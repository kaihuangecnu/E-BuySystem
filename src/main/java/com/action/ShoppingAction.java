package com.action;

import com.entity.AbstractShoppingCart;
import com.entity.Product;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ProductService;
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
import javax.servlet.http.HttpSession;

@Controller
public final class ShoppingAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

    @Resource
    private ProductService productService;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private UserService userService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String mainPage;    //购物车显示的主页
    private String navCode;     //导航栏
    private int productId;  //从页面获取商品编号
    private int productAmount;      //商品数量

    /**
     * 添加商品至购物车（ajax请求）
     */
    public String addShoppingCartItem(){
        HttpSession session=request.getSession();
        JSONObject json=new JSONObject(); //创建JSON对象
        User current_user=(User) session.getAttribute("current_user");
        if(current_user==null){
            json.put("res", "userUndefined");
        }else {
            boolean flag=false;
            for(AbstractShoppingCart cart:current_user.getShoppingCarts()){
                if(cart.getProduct().getId()==productId){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                Product product = productService.getProductById(productId);
                AbstractShoppingCart shoppingCart = new AbstractShoppingCart();
                shoppingCart.setProduct(product);
                shoppingCart.setProductAmount(1);
                shoppingCart.setUser(current_user);
                shoppingCartService.save(shoppingCart);
            }
            json.put("res", flag ? "该商品已添加至购物车！" : "商品添加成功！");
            current_user=userService.userLogin(current_user);
            session.setAttribute("current_user",current_user);  //刷新Session中的当前用户
            json.put("productNum", current_user.getShoppingCarts().size());
        }
        JSONUtil.write(response, json);
        return null;
    }

    /**
     * 查看购物车
     */
    public String findShoppingCart(){
        mainPage="shopping/shoppingCart.jsp";
        navCode= NavUtil.getNavCode(request,"我的购物车");
        return "myShoppingCart";
    }

    /**
     * 购买商品（ajax请求）
     */
    public String buyProduct() {
        User current_user = (User) request.getSession().getAttribute("current_user");
        JSONObject json = new JSONObject();
        if (current_user == null) {
            json.put("res", "userUndefined");
        } else {
            boolean flag = false;
            for (AbstractShoppingCart cart : current_user.getShoppingCarts()) {
                if (cart.getProduct().getId() == productId) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Product product = productService.getProductById(productId);
                AbstractShoppingCart shoppingCart = new AbstractShoppingCart();
                shoppingCart.setProduct(product);
                shoppingCart.setProductAmount(1);
                shoppingCart.setUser(current_user);
                shoppingCartService.save(shoppingCart);
            }
            json.put("res", "finished");
            current_user = userService.userLogin(current_user);
            request.getSession().setAttribute("current_user", current_user);  //刷新Session中的当前用户
        }
        JSONUtil.write(response, json);
        return null;
    }

    /**
     * 修改商品数量（ajax请求）
     */
    public String modifyProductAmount(){
        User current_user = (User) request.getSession().getAttribute("current_user");
        for(AbstractShoppingCart s:current_user.getShoppingCarts()){
            if(s.getProduct().getId()==productId){
                s.setProductAmount(productAmount);
                shoppingCartService.save(s);
                break;
            }
        }
        request.getSession().setAttribute("current_user",userService.userLogin(current_user));
        JSONObject json=new JSONObject();
        json.put("flag",true);
        JSONUtil.write(response,json);
        return null;
    }

    /**
     * 删除购物车中的商品
     */
    public String deleteProduct(){
        User current_user = (User) request.getSession().getAttribute("current_user");
        for(AbstractShoppingCart s:current_user.getShoppingCarts()){
            if(s.getProduct().getId()==productId){
                shoppingCartService.delete(s);
                break;
            }
        }
        request.getSession().setAttribute("current_user",userService.userLogin(current_user));
        return this.findShoppingCart();
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request=request;

    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response=response;
    }

    public String getMainPage() {
        return mainPage;
    }

    public String getNavCode() {
        return navCode;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }
}
