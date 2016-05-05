package com.util;

/**
 * 生成分页代码工具类
 * Created by SCX on 2015/12/9.
 */
public final class SplitPage {

    /**
     * 生成分页代码
     * @param url       目标地址
     * @param totalItem 总记录数
     * @param page      当前页
     * @param pageSize  每页大小
     * @param params    url重写参数
     * @return 返回分页代码
     */
    public static String pageCode(String url, long totalItem, int page, int pageSize, String params) {
        long totalPage = totalItem % pageSize == 0 ? totalItem / pageSize : totalItem / pageSize + 1;
        if (totalPage == 0) {
            return "未查询到任何数据";
        } else {
            StringBuilder pageCode = new StringBuilder("<li><a href='").append(url).append("?page=1&").append(params).append("'>首页</a></li>");
            if (page > 1)
                pageCode.append("<li><a href='").append(url).append("?page=").append(page - 1).append("&").append(params).append("'>上一页</a></li>");
            /*
             * 最多显示当前页的前3页和后3页
             */
            for (int i = page - 3; i <= page + 3; i++) {
                if (i < 1 || i > totalPage) continue;
                if (i == page)
                    pageCode.append("<li>").append(i).append("</li>");
                else
                    pageCode.append("<li><a href='").append(url).append("?page=").append(i).append("&").append(params).append("'>").append(i).append("</a></li>");
            }
            if (page < totalPage)
                pageCode.append("<li><a href='").append(url).append("?page=").append(page + 1).append("&").append(params).append("'>下一页</a></li>");
            pageCode.append("<li><a href='").append(url).append("?page=").append(totalPage).append("&").append(params).append("'>尾页</a></li>");
            return pageCode.toString();
        }
    }
}
