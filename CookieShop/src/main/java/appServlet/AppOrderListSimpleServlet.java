package appServlet;

import com.alibaba.fastjson.JSON;
import model.Msg;
import model.Order;
import model.OrderJsonNew;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AppOrderListSimpleServlet",urlPatterns = "/app/orderListSimple")
public class AppOrderListSimpleServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));

        List<Order> list = null;
        if (status == 0){
            list = oService.selectAll(id);
        }else {
            list = oService.selectAll(id,status);
        }

        ArrayList<OrderJsonNew> orderJsonNews = new ArrayList<>();
        for (int i = 0; i < list.size();i++){
            for (int j = 0;j < list.get(i).getItemList().size(); j++){
                OrderJsonNew orderJsonNew = new OrderJsonNew();
                orderJsonNew.setId(list.get(i).getId());
                orderJsonNew.setDatetime(list.get(i).getDatetime());
                orderJsonNew.setGoodsName(list.get(i).getItemList().get(j).getGoodsName());
                orderJsonNew.setPaytype(list.get(i).getPaytype());
                orderJsonNew.setTotal(list.get(i).getItemList().get(j).getAmount() * list.get(i).getItemList().get(j).getPrice());
                orderJsonNew.setAmount(list.get(i).getItemList().get(j).getAmount());

                orderJsonNews.add(orderJsonNew);
            }
        }

        Msg msg = new Msg();
        if (list.size() == 0){
            msg.setData(orderJsonNews);
            msg.setCode(false);
            msg.setMsg("所查询的用户id不存在");
        }else {
            msg.setMsg("成功查询用户相关订单列表");
            msg.setCode(true);
            msg.setData(orderJsonNews);
        }
        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
