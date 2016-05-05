package com.action;

import com.entity.AbstractShoppingCart;
import com.entity.Order;
import com.entity.Order_Product;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.OrderService;
import com.service.ShoppingCartService;
import com.service.UserService;
import com.util.JSONUtil;
import com.util.NavUtil;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public final class OrderAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

    @Resource
    private OrderService orderService;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String mainPage;
    private String navCode;
    private Order s_order;      //订单样本
    private List<Order> orders;     //当前用户的所有订单

    /*
     * 保存订单对象
     */
    public String execute(){
        User current_user = (User) request.getSession().getAttribute("current_user");
        List<Order_Product> order_products=new ArrayList<>(0);
        int sum=0;      //计算商品总价
        for(AbstractShoppingCart s:current_user.getShoppingCarts()){
            sum+=s.getProductAmount()*s.getProduct().getPrice();
            order_products.add(new Order_Product(s.getProductAmount(),s.getProduct()));
            shoppingCartService.delete(s);      //删除购物车的商品
        }
        Order order=new Order(UUID.randomUUID().toString(),new Date(),sum,1,current_user,order_products);
        orderService.save(order);       //保存订单信息
        request.getSession().setAttribute("current_user",userService.userLogin(current_user));
        navCode= NavUtil.getNavCode(request,"我的订单");
        mainPage="shopping/shopping-result.jsp";
        return SUCCESS;
    }

    /*
     * 获取当前所需的订单（有可能是当前用户，或者是指定订单号模糊查询的结果）
     */
    public String getRequiredOrders(){
        User current_user= (User) request.getSession().getAttribute("current_user");
        /*
         *  s_order为空，是查看当前用户的所有订单；
         *  否则是当前用户进行模糊搜索的订单
         */
        if(s_order==null)
            s_order=new Order();
        s_order.setUser(current_user);
        orders=orderService.getRequiredOrders(s_order,null);
        navCode= NavUtil.getNavCode(request,"个人订单");
        mainPage="userCenter/myOrders.jsp";
        return "my-orders";
    }

    /*
     * 修改订单状态（Ajax请求）
     */
    public void updateOrderStatus(){
        orderService.updateStatus(s_order);
        JSONObject json=new JSONObject();
        json.put("orderUpdated",true);
        JSONUtil.write(response, json);
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    public String getMainPage() {
        return mainPage;
    }

    public String getNavCode() {
        return navCode;
    }

    public Order getS_order() {
        return s_order;
    }

    public void setS_order(Order s_order) {
        this.s_order = s_order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response=response;
    }
}
