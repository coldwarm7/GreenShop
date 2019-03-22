package appServlet;

import com.alibaba.fastjson.JSON;
import model.Msg;
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

@WebServlet(name = "AppGoodsByKindServlet",urlPatterns = "/app/goodsByKind")
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

        Msg msg = new Msg();
        msg.setData(list);
        msg.setCode(true);
        if (typeId == 1){
            msg.setMsg("成功查询首推商品");
        }else if (typeId == 2){
            msg.setMsg("成功查询热销商品");
        }else if (typeId == 3){
            msg.setMsg("成功查询新品");
        }
        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }
}
