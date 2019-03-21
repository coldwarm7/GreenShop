package appServlet;

import com.alibaba.fastjson.JSON;
import model.Type;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "AppGoodsTypeServlet",urlPatterns = "/app/goods_type")
public class AppGoodsTypeServlet extends HttpServlet {
    private TypeService tService = new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> list= tService.GetAllType();
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String userJson = JSON.toJSONString(list);
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }
}
