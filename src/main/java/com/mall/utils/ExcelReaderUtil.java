package com.mall.utils;

import com.mall.service.IRowReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangtao on 2018/7/5
 */
@Component
public class ExcelReaderUtil {

    /**
     * 读取Excel文件，可能是03也可能是07版本
     *
     * @param excel03
     * @param excel07
     * @param fileName
     * @throws Exception
     */
    private static IRowReader rowReader;

    @Autowired
    public void setRowReader(IRowReader rowReader) {
        ExcelReaderUtil.rowReader = rowReader;
    }

    public static void readExcel(String fileName) throws Exception {
        Excel2007Reader excel07 = new Excel2007Reader();
        excel07.setRowReader(rowReader);
        excel07.processOneSheet(fileName);
    }

}
