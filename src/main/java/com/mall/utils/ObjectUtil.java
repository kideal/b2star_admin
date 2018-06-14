package com.mall.utils;


import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;


/**
 * Miscellaneous object utility methods. Mainly for internal use within the
 * framework;
 */
public abstract class ObjectUtil {


    /**
     * Check whether the given value can be treated as a String value.
     */
    public static boolean isString(Class<?> inValueType) {
        // Consider any CharSequence (including StringBuffer and StringBuilder)
        // as a String.
        return (CharSequence.class.isAssignableFrom(inValueType) || StringWriter.class.isAssignableFrom(inValueType));
    }

    /**
     * Check whether the given value is a <code>java.util.Date</code> (but not
     * one of the JDBC-specific subclasses).
     */
    public static boolean isDate(Class<?> inValueType) {
        return (Date.class.isAssignableFrom(inValueType) && !(java.sql.Date.class.isAssignableFrom(inValueType) || java.sql.Time.class.isAssignableFrom(inValueType) || java.sql.Timestamp.class
                .isAssignableFrom(inValueType)));
    }

    public static final String EMPTY_STRING = "";

    public static final String NULL_STRING = "null";

    @SuppressWarnings("rawtypes")
    public static Boolean isInstance(Class targetClazz, Object obj) {
        return targetClazz.isInstance(obj);
    }


    public static Boolean isPrimitive(Object obj) {
        return obj.getClass().isPrimitive();
    }

    public static Boolean isNumber(Object obj) {
        return Number.class.isInstance(obj);
    }


    public static Boolean isDate(Object obj) {
        return Date.class.isInstance(obj) || Calendar.class.isInstance(obj);
    }


    public static Boolean isString(Object obj) {
        return isInstance(String.class, obj);
    }

    /**
     * Determine whether the given object is an array: either an Object array or
     * a primitive array.
     *
     * @param obj the object to check
     */
    public static boolean isArray(Object obj) {
        return ((obj != null) && obj.getClass().isArray());
    }

    public static boolean isCollection(Object obj) {
        return ((obj != null) && obj instanceof Collection);
    }

    public static boolean isMap(Object obj) {
        return ((obj != null) && obj instanceof Map);
    }

    /**
     * Return whether the given throwable is a checked exception: that is,
     * neither a RuntimeException nor an Error.
     *
     * @param ex the throwable to check
     * @return whether the throwable is a checked exception
     * @see Exception
     * @see RuntimeException
     * @see Error
     */
    public static boolean isCheckedException(Throwable ex) {
        return !((ex instanceof RuntimeException) || (ex instanceof Error));
    }

    /**
     * Check whether the given exception is compatible with the exceptions
     * declared in a throws clause.
     *
     * @param ex                 the exception to checked
     * @param declaredExceptions the exceptions declared in the throws clause
     * @return whether the given exception is compatible
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean isCompatibleWithThrowsClause(Throwable ex, Class[] declaredExceptions) {
        if (!isCheckedException(ex)) {
            return true;
        }
        if (declaredExceptions != null) {
            int i = 0;
            while (i < declaredExceptions.length) {
                if (declaredExceptions[i].isAssignableFrom(ex.getClass())) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;
        if (obj instanceof String) {
            return ((String) obj).isEmpty();
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Array) {
            return Array.getLength((Array) obj) < 1;
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }

    /**
     * Determine whether the given charter string is empty: i.e. null
     *
     * @return Boolean
     */
    public static Boolean isNullOrEmpty(String target) {
        return target == null || target.trim().length() < 1;
    }

    public static Boolean NotEmpty(String target) {
        return !isNullOrEmpty(target);
    }

    /**
     * Determine whether the given array is empty: i.e. <code>null</code> or of
     * zero length.
     *
     * @param array the array to check
     */
    public static boolean isNullOrEmpty(Object[] array) {
        return ((array == null) || (array.length == 0));
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isNullOrEmpty(array);
    }

    /**
     * Return <code>true</code> if the supplied Collection is <code>null</code>
     * or empty. Otherwise, return <code>false</code>.
     *
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Collection collection) {
        return ((collection == null) || collection.isEmpty());
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Collection collection) {
        return !isNullOrEmpty(collection);
    }

    /**
     * Return <code>true</code> if the supplied Map is <code>null</code> or
     * empty. Otherwise, return <code>false</code>.
     *
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNullOrEmpty(Map map) {
        return ((map == null) || map.isEmpty());
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Map map) {
        return !isNullOrEmpty(map);
    }

    /*
     * public static boolean equals(Object x, Object y) { return (x == y) || ((x
     * != null) && (y != null) && x.equals(y)); }
     */

    /**
     * Determine if the given objects are equal, returning <code>true</code> if
     * both are <code>null</code> or <code>false</code> if only one is
     * <code>null</code>.
     * <p>
     * Compares arrays with <code>Arrays.equals</code>, performing an equality
     * check based on the array elements rather than the array reference.
     *
     * @param o1 first Object to compare
     * @param o2 second Object to compare
     * @return whether the given objects are equal
     * @see Arrays#equals
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if ((o1 == null) || (o2 == null)) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        if (isArray(o1) && isArray(o2)) {
            if ((o1 instanceof Object[]) && (o2 instanceof Object[])) {
                return Arrays.equals((Object[]) o1, (Object[]) o2);
            }
            if ((o1 instanceof boolean[]) && (o2 instanceof boolean[])) {
                return Arrays.equals((boolean[]) o1, (boolean[]) o2);
            }
            if ((o1 instanceof byte[]) && (o2 instanceof byte[])) {
                return Arrays.equals((byte[]) o1, (byte[]) o2);
            }
            if ((o1 instanceof char[]) && (o2 instanceof char[])) {
                return Arrays.equals((char[]) o1, (char[]) o2);
            }
            if ((o1 instanceof double[]) && (o2 instanceof double[])) {
                return Arrays.equals((double[]) o1, (double[]) o2);
            }
            if ((o1 instanceof float[]) && (o2 instanceof float[])) {
                return Arrays.equals((float[]) o1, (float[]) o2);
            }
            if ((o1 instanceof int[]) && (o2 instanceof int[])) {
                return Arrays.equals((int[]) o1, (int[]) o2);
            }
            if ((o1 instanceof long[]) && (o2 instanceof long[])) {
                return Arrays.equals((long[]) o1, (long[]) o2);
            }
            if ((o1 instanceof short[]) && (o2 instanceof short[])) {
                return Arrays.equals((short[]) o1, (short[]) o2);
            }
        }
        return false;
    }

    /**
     * Determine the class name for the given object.
     * <p>
     * Returns <code>"null"</code> if <code>obj</code> is <code>null</code>.
     *
     * @param obj the object to introspect (may be <code>null</code>)
     * @return the corresponding class name
     */
    public static String getClassName(Object obj) {
        return (obj != null ? obj.getClass().getName() : NULL_STRING);
    }


    /**
     * Return a String representation of an object's overall identity.
     *
     * @param obj the object (may be <code>null</code>)
     * @return the object's identity as String representation, or an empty
     * String if the object was <code>null</code>
     */
    public static String getIdentity(Object obj) {
        return obj == null ? EMPTY_STRING : obj.getClass().getName() + "@" + getHexIdentity(obj);
    }

    /**
     * Return a hex String form of an object's identity hash code.
     *
     * @param obj the object
     * @return the object's identity code in hex notation
     */
    public static String getHexIdentity(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    /**
     * Return the same value as <code>{@link Boolean#hashCode()}</code>.
     *
     * @see Boolean#hashCode()
     */
    public static int hashCode(boolean bool) {
        return bool ? 1231 : 1237;
    }

    /**
     * Return the same value as <code>{@link Long#hashCode()}</code>.
     *
     * @see Byte#hashCode()
     */
    public static int hashCode(byte value) {
        return (int) value;
    }

    /**
     * Return the same value as <code>{@link Long#hashCode()}</code>.
     *
     * @see Character#hashCode()
     */
    public static int hashCode(char value) {
        return (int) value;
    }

    // ---------------------------------------------------------------------
    // Convenience methods for content-based equality/hash-code handling
    // ---------------------------------------------------------------------

    /**
     * Return the same value as <code>{@link Double#hashCode()}</code>.
     *
     * @see Double#hashCode()
     */
    public static int hashCode(double dbl) {
        long bits = Double.doubleToLongBits(dbl);
        return hashCode(bits);
    }

    /**
     * Return the same value as <code>{@link Float#hashCode()}</code>.
     *
     * @see Float#hashCode()
     */
    public static int hashCode(float flt) {
        return Float.floatToIntBits(flt);
    }

    /**
     * Return the same value as <code>{@link Long#hashCode()}</code>.
     *
     * @see Long#hashCode()
     */
    public static int hashCode(long lng) {
        return (int) (lng ^ (lng >>> 32));
    }

    /**
     * Return the same value as <code>{@link Long#hashCode()}</code>.
     *
     * @see Short#hashCode()
     */
    public static int hashCode(short value) {
        return (int) value;
    }

    /**
     * Return the same value as <code>{@link Long#hashCode()}</code>.
     *
     * @see Integer#hashCode()
     */
    public static int hashCode(int value) {
        return value;
    }

    /**
     * Return the same value as <code>{@link Long#hashCode()}</code>.
     *
     * @see Integer#hashCode()
     */
    public static Object ifNullValue(Object value, Object convertValue) {
        if (value == null) {
            return convertValue;
        }
        return value;
    }


    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }


    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    private static Field getFieldByName(String name, Field[] fields) {
        for (Field field : fields) {
            String fname = field.getName();
            if (fname.equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 将map 中的数据复制到对象中
     *
     * @param target
     * @param map
     */
    public static void setFieldByFromMap(Object target, Map<String, Object> map) {
        map.forEach((key, value) -> {
            setFieldByKey(key, target, value);
        });
    }

    public static void setFieldByKey(String key, Object target, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, target, value);
        } catch (NoSuchFieldException e) {
            System.out.println("对象没有属性:" + key);
            e.printStackTrace();
        }
    }

    public static String[] arraySplit(String arrString) {
        String[] arr = arrString.split(",");
        return arr;
    }

}
