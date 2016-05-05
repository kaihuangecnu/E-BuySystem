package com.util;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 将Java对象中的集合属性所保存对象的部分字段转换为JSON
 */
public class ObjectToJsonProcessor implements JsonValueProcessor{

    private String[] props;

    private Class clazz;

    /**
     * 将Java对象中的集合属性所保存对象的部分字段转换为JSON
     * @param props 要保留的字段
     * @param clazz 类的class类型
     */
    public ObjectToJsonProcessor(String[] props, Class clazz) {
        this.props = props;
        this.clazz = clazz;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        PropertyDescriptor descriptor;
        Method method;
        StringBuilder json=new StringBuilder("{");
        try {
            for (int i = 0; i < props.length; i++) {
                descriptor=new PropertyDescriptor(props[i],clazz);
                method=descriptor.getReadMethod();
                String value= String.valueOf(method.invoke(o));
                json.append("'").append(props[i]).append("':'").append(value).append("'");
                json.append(i != props.length - 1 ? "," : "");
            }
            json.append("}");
        }catch (IntrospectionException | InvocationTargetException | IllegalAccessException e){
            e.printStackTrace();
        }
        return JSONObject.fromObject(json.toString());
    }
}
