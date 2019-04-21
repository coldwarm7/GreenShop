package appServlet;

import com.alibaba.fastjson.JSON;
import model.Msg;
import model.Order;
import model.Type;
import model.User;
import net.sf.json.JSONObject;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "AppOrderListServlet",urlPatterns = "/app/orderList")
public class AppOrderListServlet extends HttpServlet {
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


        Msg msg = new Msg();
        if (list.size() == 0){
            msg.setData(list);
            msg.setCode(false);
            msg.setMsg("所查询的用户id不存在");
        }else {
            msg.setMsg("成功查询用户相关订单列表");
            msg.setCode(true);
            msg.setData(list);
        }
        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
