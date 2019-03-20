package appServlet;

import com.alibaba.fastjson.JSON;
import model.Goods;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(name = "AppGoodsSearchServlet",urlPatterns = "/greenshop/app/goodsSearch")
public class AppGoodsSearchServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String keywords = request.getParameter("keyword");
        List<Goods> list = goodsService.getSearchGoods(keywords);

        String registerJson = JSON.toJSONString(list);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
