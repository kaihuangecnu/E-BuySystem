package com.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class JSONUtil {

    /**
     * 将JSON对象输出到页面
     * @param response HttpServletResponse对象
     * @param json JSON对象
     */
    public static void write(HttpServletResponse response,Object json){
        response.setContentType("text/html; charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.print(json.toString());
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
