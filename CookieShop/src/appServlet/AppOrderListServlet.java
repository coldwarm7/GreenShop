package appServlet;

import com.alibaba.fastjson.JSON;
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

@WebServlet(name = "AppOrderListServlet",urlPatterns = "/greenshop/app/orderList")
public class AppOrderListServlet extends HttpServlet {
    private OrderService oService = new OrderService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        List<Order> list = oService.selectAll(id);

        String registerJson = JSON.toJSONString(list);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
