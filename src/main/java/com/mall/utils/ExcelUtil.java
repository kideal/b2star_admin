package com.mall.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huangtao on 2018/7/3
 */
public class ExcelUtil {
    private static StylesTable stylesTable;
    private static Integer row = 1;
    private static List<String> title = Lists.newArrayList();

    /**
     * 处理一个sheet
     *
     * @param filename
     * @throws Exception
     */
    public static void processOneSheet(String filename) throws Exception {
        //POI根据xlsx文档的路径path获取到文件File - file
        OPCPackage pkg = OPCPackage.open(filename);
        //从zip中获取到[Content_Types].xml
        XSSFReader r = new XSSFReader(pkg);
        stylesTable = r.getStylesTable();
        // 获取当前Excel所有Sheet中字符串
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(sst);

        // Seems to either be rId# or rSheet#
        InputStream sheet2 = r.getSheet("rId1");
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);
        sheet2.close();
    }

    /**
     * 处理所有sheet
     *
     * @param filename
     * @throws Exception
     */
    public void processAllSheets(String filename) throws Exception {

        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(sst);

        Iterator<InputStream> sheets = r.getSheetsData();
        while (sheets.hasNext()) {
            System.out.println("Processing new sheet:\n");
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
            System.out.println("");
        }
    }

    /**
     * 获取解析器
     *
     * @param sst
     * @return
     * @throws SAXException
     */
    public static XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser =
                XMLReaderFactory.createXMLReader(
                        "org.apache.xerces.parsers.SAXParser"
                );
        ContentHandler handler = new ExcelUtil.SheetHandler(sst);
        parser.setContentHandler(handler);
        return parser;
    }
    private static class SheetHandler extends DefaultHandler {

        private SharedStringsTable sst;
        private String lastContents;
        private boolean nextIsString;

        private List<String> rowlist = new ArrayList<String>();
        private int curRow = 0;
        private int curCol = 0;

        //定义前一个元素和当前元素的位置，用来计算其中空的单元格数量，如A6和A8等
        private String preRef = null, ref = null;
        //定义该文档一行最大的单元格数，用来补全一行最后可能缺失的单元格
        private String maxRef = null;

        private ExcelUtil.SheetHandler.CellDataType nextDataType = ExcelUtil.SheetHandler.CellDataType.SSTINDEX;
        private final DataFormatter formatter = new DataFormatter();
        private short formatIndex;
        private String formatString;
        private HashMap<String, String> map = Maps.newHashMap();
        //用一个enum表示单元格可能的数据类型
        enum CellDataType {
            BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
        }

        private SheetHandler(SharedStringsTable sst) {
            this.sst = sst;
        }

        /**
         * 解析一个element的开始时触发事件
         */
        public void startElement(String uri, String localName, String name,
                                 Attributes attributes) throws SAXException {

            // c => cell
            if (name.equals("c")) {
                //前一个单元格的位置
                if (preRef == null) {
                    preRef = attributes.getValue("r");
                } else {
                    preRef = ref;
                }
                //当前单元格的位置
                ref = attributes.getValue("r");

                this.setNextDataType(attributes);

                // Figure out if the value is an index in the SST
                String cellType = attributes.getValue("t");
                if (cellType != null && cellType.equals("s")) {
                    nextIsString = true;
                } else {
                    nextIsString = false;
                }

            }
            // Clear contents cache
            lastContents = "";
        }

        /**
         * 根据element属性设置数据类型
         *
         * @param attributes
         */
        public void setNextDataType(Attributes attributes) {

            nextDataType = ExcelUtil.SheetHandler.CellDataType.NUMBER;
            formatIndex = -1;
            formatString = null;
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType)) {
                nextDataType = ExcelUtil.SheetHandler.CellDataType.BOOL;
            } else if ("e".equals(cellType)) {
                nextDataType = ExcelUtil.SheetHandler.CellDataType.ERROR;
            } else if ("inlineStr".equals(cellType)) {
                nextDataType = ExcelUtil.SheetHandler.CellDataType.INLINESTR;
            } else if ("s".equals(cellType)) {
                nextDataType = ExcelUtil.SheetHandler.CellDataType.SSTINDEX;
            } else if ("str".equals(cellType)) {
                nextDataType = ExcelUtil.SheetHandler.CellDataType.FORMULA;
            }
            if (cellStyleStr != null) {
                int styleIndex = Integer.parseInt(cellStyleStr);
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                formatIndex = style.getDataFormat();
                formatString = style.getDataFormatString();
                if ("m/d/yy" == formatString) {
                    nextDataType = ExcelUtil.SheetHandler.CellDataType.DATE;
                    //full format is "yyyy-MM-dd hh:mm:ss.SSS";
                    formatString = "yyyy-MM-dd";
                }
                if (formatString == null) {
                    nextDataType = ExcelUtil.SheetHandler.CellDataType.NULL;
                    formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
                }
            }
        }

        /**
         * 解析一个element元素结束时触发事件
         */
        public void endElement(String uri, String localName, String name)
                throws SAXException {
            // Process the last contents as required.
            // Do now, as characters() may be called more than once
            if (nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                nextIsString = false;
            }

            // v => contents of a cell
            // Output after we've seen the string contents
            if (name.equals("v")) {
                String value = this.getDataValue(lastContents.trim(), "");
                //补全单元格之间的空单元格
                if (!ref.equals(preRef)) {
                    int len = countNullCell(ref, preRef);
                    for (int i = 0; i < len; i++) {
                        rowlist.add(curCol, "");
                        curCol++;
                    }
                }
                rowlist.add(curCol, value);
                curCol++;
            } else {
                //如果标签名称为 row，这说明已到行尾，调用 optRows() 方法
                if (name.equals("row")) {
                    String value = "";
                    //默认第一行为表头，以该行单元格数目为最大数目
                    if (curRow == 0) {
                        maxRef = ref;
                    }
                    //补全一行尾部可能缺失的单元格
                    if (maxRef != null) {
                        int len = countNullCell(maxRef, ref);
                        for (int i = 0; i <= len; i++) {
                            rowlist.add(curCol, "");
                            curCol++;
                        }
                    }
                    curRow++;
                    // System.out.println(rowlist);
                    /*数据打包*/
                    if (row == 1) {
                        for (String s : rowlist) {
                            title.add(s);
                        }
                    }else {
                        for (int i = 0; i <rowlist.size() ; i++) {
                            map.put(title.get(i), rowlist.get(i));
                        }
                    }
                    System.out.println(map);
                    row++;
                    //一行的末尾重置一些数据
                    rowlist.clear();
                    curCol = 0;
                    preRef = null;
                    ref = null;
                }
            }
        }

        /**
         * 根据数据类型获取数据
         *
         * @param value
         * @param thisStr
         * @return
         */
        public String getDataValue(String value, String thisStr)

        {
            switch (nextDataType) {
                //这几个的顺序不能随便交换，交换了很可能会导致数据错误
                case BOOL:
                    char first = value.charAt(0);
                    thisStr = first == '0' ? "FALSE" : "TRUE";
                    break;
                case ERROR:
                    thisStr = "\"ERROR:" + value.toString() + '"';
                    break;
                case FORMULA:
                    thisStr = '"' + value.toString() + '"';
                    break;
                case INLINESTR:
                    XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                    thisStr = rtsi.toString();
                    rtsi = null;
                    break;
                case SSTINDEX:
                    String sstIndex = value.toString();
                    thisStr = value.toString();
                    break;
                case NUMBER:
                    if (formatString != null) {
                        thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
                    } else {
                        thisStr = value;
                    }
                    thisStr = thisStr.replace("_", "").trim();
                    break;
                case DATE:
                    try {
                        thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString);
                    } catch (NumberFormatException ex) {
                        thisStr = value.toString();
                    }
                    thisStr = thisStr.replace(" ", "");
                    break;
                default:
                    thisStr = "";
                    break;
            }
            return thisStr;
        }

        /**
         * 获取element的文本数据
         */
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            lastContents += new String(ch, start, length);
        }

        /**
         * 计算两个单元格之间的单元格数目(同一行)
         *
         * @param ref
         * @param preRef
         * @return
         */
        public int countNullCell(String ref, String preRef) {
            //excel2007最大行数是1048576，最大列数是16384，最后一列列名是XFD
            String xfd = ref.replaceAll("\\d+", "");
            String xfd_1 = preRef.replaceAll("\\d+", "");

            xfd = fillChar(xfd, 3, '@', true);
            xfd_1 = fillChar(xfd_1, 3, '@', true);

            char[] letter = xfd.toCharArray();
            char[] letter_1 = xfd_1.toCharArray();
            int res = (letter[0] - letter_1[0]) * 26 * 26 + (letter[1] - letter_1[1]) * 26 + (letter[2] - letter_1[2]);
            return res - 1;
        }

        /**
         * 字符串的填充
         *
         * @param str
         * @param len
         * @param let
         * @param isPre
         * @return
         */
        String fillChar(String str, int len, char let, boolean isPre) {
            int len_1 = str.length();
            if (len_1 < len) {
                if (isPre) {
                    for (int i = 0; i < (len - len_1); i++) {
                        str = let + str;
                    }
                } else {
                    for (int i = 0; i < (len - len_1); i++) {
                        str = str + let;
                    }
                }
            }
            return str;
        }
    }
}