package com.util;

import javax.servlet.http.HttpServletRequest;

public final class NavUtil{

    /**
     * 生成导航条代码
     * @param request 根据request对象获取上下文路径
     * @param navName 子节点
     * @return HTML代码
     */
    public static String getNavCode(HttpServletRequest request,String navName){
        return "您现在的位置：<a href='"+request.getContextPath()+"/index.jsp'>首页</a>&nbsp;&gt;"+navName;
    }
}
