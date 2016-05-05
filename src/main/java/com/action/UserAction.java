package com.action;

import com.entity.PageBean;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;
import com.util.DateToJsonValueProcessor;
import com.util.JSONUtil;
import com.util.NavUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public final class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    @Resource
    private UserService userService;
    private User s_user;  //getter和setter不能随便删减
    private String userName;
    private String error;   //用户登录失败的错误信息
    private String imageCode;   //用于接收用户输入的验证码
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String navCode;
    private String mainPage;
//    page,rows分别是easyUI传入的分页参数
    private int page;//当前页
    private int rows;//返回的数据集
    private String ids;//页面被选中的id

    /**
     * 用户注册
     */
    public String userReg(){
        userService.saveOrUpdateUser(s_user);
        return SUCCESS;
    }

    //通过ajax提交请求
    public void saveUser(){
        userService.saveOrUpdateUser(s_user);
        JSONObject json=new JSONObject();
        json.put("status","success");
        JSONUtil.write(response,json);
    }

    /**
     * 跳转到用户个人中心页
     */
    public String preUserUpdate() {
        navCode = NavUtil.getNavCode(request,"个人中心");
        mainPage = "userCenter/userDefault.jsp";
        return "userUpdate";
    }

    /**
     * 显示登录用户的个人信息
     */
    public String getUserInfo() {
        navCode = NavUtil.getNavCode(request,"我的资料");
        mainPage = "userCenter/userInfo.jsp";
        return "userUpdate";
    }

    /**
     * 转到用户修改信息修改页
     */
    public String userUpdate() {
        navCode = NavUtil.getNavCode(request,"修改资料");
        mainPage = "userCenter/userSave.jsp";
        return "userUpdate";
    }

    /**
     * 保存修改用户
     */
    public String saveUpdatedUser() {
        userService.saveOrUpdateUser(s_user);
        navCode = NavUtil.getNavCode(request,"我的资料");
        mainPage = "userCenter/userInfo.jsp";
        request.getSession().setAttribute("current_user", userService.userLogin(s_user));
        return "userUpdate";
    }

//    修改用户（ajax请求）
    public void updateUser(){
        userService.saveOrUpdateUser(s_user);
        JSONObject json=new JSONObject();
        json.put("status","success");
        JSONUtil.write(response,json);
    }

    /**
     * 判断用户名是否存在（ajax请求）
     */
    public void userNameExisted() {
        boolean existed = userService.userNameIsExisted(userName);
        JSONObject data = new JSONObject();
        data.put("userExisted", existed);
        JSONUtil.write(response, data);
    }

    /**
     * 用户登录
     */
    public String userLogin() {
        String sessionKey = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if (!sessionKey.equals(imageCode)) {
            this.error = "验证码错误";
            return ERROR;
        } else {
            User current_user = userService.userLogin(this.s_user);
            if (current_user == null) {
                this.error = "用户名或密码错误";
                return ERROR;
            } else {
                if(current_user.getStatus()==this.s_user.getStatus()) {
                    request.getSession().setAttribute("current_user", current_user);
                    return current_user.getStatus() == 1 ? "admin-success" : "login-success";
                }else {
                    if(current_user.getStatus()==0){
                        this.error="对不起，您没有管理员权限";
                        return ERROR;
                    }else {
                        request.getSession().setAttribute("current_user", current_user);
                        return "login-success";
                    }
                }
            }
        }
    }

    /**
     * 用户注销
     */
    public String logout() {
        request.getSession().setAttribute("current_user", null);
        return "logout-success";
    }

    /**
     * 查询User
     */
    public void findUser(){
        List<User> users=userService.queryUser(s_user,new PageBean(page,rows));
        long total=userService.getUserCount(s_user);
        JsonConfig config=new JsonConfig();
        config.setExcludes(new String[]{"orderList","shoppingCarts"});
        config.registerJsonValueProcessor(Date.class,new DateToJsonValueProcessor("yyyy年MM月dd日"));
        JSONArray rows=JSONArray.fromObject(users, config);
        JSONObject json=new JSONObject();
        json.put("rows",rows);
        json.put("total",total);
        JSONUtil.write(response,json);
    }

    /**
     * 删除User
     */
    public void deleteUsers(){
        int num=userService.deleteUser(ids);
        JSONObject json=new JSONObject();
        json.put("deleteNum",num);
        json.put("status","success");
        JSONUtil.write(response,json);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getS_user() {
        return s_user;
    }

    public void setS_user(User s_user) {
        this.s_user = s_user;
    }

    public String getError() {
        return error;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getNavCode() {
        return navCode;
    }

    public String getMainPage() {
        return mainPage;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
