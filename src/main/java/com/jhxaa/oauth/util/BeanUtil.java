package com.jhxaa.oauth.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 安
 * @Date: 2020/06/13/11:20
 * @Description:
 */
@SuppressWarnings("unchecked")
public class BeanUtil {

    public static final String ENTITY_FUNCATION_BEGIN_SET_NAME = "set";
    public static final String ENTITY_FUNCATION_FILTER_SERIALVERSIONUID_NAME = "serialVersionUID";

    /**
     * 获取对象路径
     *
     * @param c
     * @return
     */
    public static String getName(Class<?> c) {
        if (Objects.isNull(c)) {
            throw new NullPointerException("object not null.");
        }
        return c.getName();
    }

    public static <T> T newInstance(Class<T> c) {
        T t = null;
        try {
            t = (T) Class.forName(getName(c)).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static Map<String, Object> toMap(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(o));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    static String getEntityFuncationSetName(String s) {
        StringBuilder builder = new StringBuilder(ENTITY_FUNCATION_BEGIN_SET_NAME);
        builder.append(s.substring(0, 1).toUpperCase());
        builder.append(s.substring(1, s.length()));
        return builder.toString();
    }

    public static <T> T toBean(Map<String, Object> m, Class<T> c) {
        T t = null;
        String funcationName, fieldName;
        Class<?> type;
        Method method;
        try {
            t = c.newInstance();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                fieldName = field.getName();
                if (ENTITY_FUNCATION_FILTER_SERIALVERSIONUID_NAME.equals(fieldName)) {
                    continue;
                }
                type = c.getDeclaredField(fieldName).getType();
                funcationName = getEntityFuncationSetName(fieldName);
                method = c.getMethod(funcationName, type);
                if (type.isAssignableFrom(String.class)) {
                    method.invoke(t, String.valueOf(m.get(fieldName)));
                } else if (type.isAssignableFrom(boolean.class) || type.isAssignableFrom(Boolean.class)) {
                    method.invoke(t, Boolean.parseBoolean(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class)) {
                    method.invoke(t, Byte.parseByte(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(char.class) || type.isAssignableFrom(Character.class)) {
                    method.invoke(t, (char) m.get(fieldName));
                } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
                    method.invoke(t, Short.parseShort(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                    method.invoke(t, Integer.parseInt(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
                    method.invoke(t, Long.parseLong(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
                    method.invoke(t, Short.parseShort(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
                    method.invoke(t, Float.parseFloat(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(double.class) || type.isAssignableFrom(Double.class)) {
                    method.invoke(t, Double.parseDouble(String.valueOf(m.get(fieldName))));
                } else if (type.isAssignableFrom(Date.class)) {
                    method.invoke(t, (Date) m.get(fieldName));
                } else if (type.isAssignableFrom(Timestamp.class)) {
                    method.invoke(t, (Timestamp) m.get(fieldName));
                } else {
                    System.out.println(m.get(fieldName));
                    method.invoke(t, m.get(fieldName));
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }


    public static String toMapString(Object o) {
        Map<String, Object> toMap = toMap(o);
        StringBuilder builder = new StringBuilder();
        toMap.forEach((key, value) -> {
            builder.append(key);
            builder.append("=");
            builder.append(value == null ? "" : value);
            builder.append(",");
        });
        builder.substring(0, builder.length() - 1);
        return builder.toString();
    }

    public static Map stringToMap(String str) {
        HashMap<String, Object> map = new HashMap<>();
        String[] split = str.split(",");
        String[] strings = null;
        for (String s : split) {
            strings = s.split("=");
            map.put(strings[0], strings.length == 2 ? strings[1] : "");
        }
        return map;
    }

    public static void main(String[] args) throws Exception {

    }
}
