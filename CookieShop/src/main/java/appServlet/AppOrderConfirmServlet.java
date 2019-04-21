package appServlet;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Or;
import model.*;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import service.GoodsService;
import service.OrderService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@WebServlet(name = "AppOrderConfirmServlet",urlPatterns = "/app/order_confirm")
public class AppOrderConfirmServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    private UserService userService = new UserService();
    private GoodsService goodsService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject json=JsonReader.receivePost(request);
        Msg msg = new Msg();

        Map<Object,Object> map = new HashMap<>();
        map.put("itemList",OrderItem.class);
        OrderJson o = (OrderJson) JSONObject.toBean(json,OrderJson.class,map);

        User user = userService.selectById(o.getUser_id());
        Order order = new Order();
        order.setUser(user);
        order.setItemList(o.getItemList());
        order.setPaytype(o.getPaytype());
        order.setStatus(o.getStatus());
        order.setDatetime(new Date());
        order.setName(user.getName());
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());

        float totalPrice = 0;
        int totalAmount = 0;
        List<OrderItem> orderItems = o.getItemList();

        for (OrderItem orderItem : orderItems){
            totalAmount = totalAmount + orderItem.getAmount();
            totalPrice = totalPrice + orderItem.getAmount() * goodsService.getGoodsById(orderItem.getGoods_id()).getPrice();
            if (goodsService.getGoodsById(orderItem.getGoods_id()) == null || goodsService.getGoodsById(orderItem.getGoods_id()).getStock() <= 0){
                msg.setMsg("商品不存在，订单创建失败");
                msg.setCode(false);
                List<User> users = new ArrayList<>();
                msg.setData(users);
                String registerJson = JSON.toJSONString(msg);
                OutputStream out = response.getOutputStream();
                out.write(registerJson.getBytes("UTF-8"));
                out.flush();
                return;
            }
            Goods goods = goodsService.getGoodsById(orderItem.getGoods_id());
            order.addGoods(goods,orderItem.getAmount());
        }

        order.setAmount(totalAmount);
        order.setTotal(totalPrice);

        oService.addOrder(order);

        msg.setCode(true);
        msg.setMsg("订单支付成功！");
        List<User> users = new ArrayList<>();
        msg.setData(users);
        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
