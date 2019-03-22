package appServlet;

import com.alibaba.fastjson.JSON;
import model.Goods;
import model.Msg;
import model.User;
import net.sf.json.JSONObject;
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

@WebServlet(name = "AppGoodsListServlet",urlPatterns = "/app/goodsList")
public class AppGoodsListServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        List<Map<String,Object>> goods = goodsService.findAll(typeId);
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        Msg msg = new Msg();
        msg.setData(goods);
        msg.setCode(true);
        if (typeId == 0){
            msg.setMsg("成功查询所有商品");
        }else {
            msg.setMsg("成功按照分类查找商品");
        }
        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
