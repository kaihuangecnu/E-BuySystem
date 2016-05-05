package com.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public final class DateToJsonValueProcessor implements JsonValueProcessor{

	private String format;

    /**
     * 在Java对象转换为JSON时将Date转换为字符串
     * @param format 要转换的日期格式
     */
    public DateToJsonValueProcessor(String format) {
        this.format = format;
    }

    public DateToJsonValueProcessor() {
        this.format="yyyy-MM-dd";
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }

    @Override
    public Object processObjectValue(String s, Object obj, JsonConfig jsonConfig) {
        if(obj instanceof Timestamp || obj instanceof Date){
            return new SimpleDateFormat(format).format(obj);
        }
        return obj.toString();
    }
}
