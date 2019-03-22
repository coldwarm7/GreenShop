package appServlet;

import com.alibaba.fastjson.JSON;
import model.Goods;
import model.Msg;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "AppGoodsByIdServlet",urlPatterns = "/app/goodsById")
public class AppGoodsByIdServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        int goods_id = Integer.parseInt(request.getParameter("goods_id"));
        Goods g = goodsService.getGoodsById(goods_id);

        Msg msg = new Msg(true,"根据id查询商品成功",g);
        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
