package appServlet;

import com.alibaba.fastjson.JSON;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

//热销或新品

@WebServlet(name = "AppGoodsByKindServlet",urlPatterns = "/greenshop/app/goodsByKind")
public class AppGoodsByTypeServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        int typeId = Integer.parseInt(request.getParameter("kindId"));
        List<Map<String,Object>> list = goodsService.getGoodsList(typeId);
        String registerJson = JSON.toJSONString(list);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
