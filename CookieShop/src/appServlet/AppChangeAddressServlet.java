package appServlet;


import com.alibaba.fastjson.JSON;
import model.Msg;
import model.User;
import net.sf.json.JSONObject;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "AppChangeAddressServlet",urlPatterns = "/app/user_changeaddress")
public class AppChangeAddressServlet extends HttpServlet {

    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject json=JsonReader.receivePost(request);
        User loginUser = (User) JSONObject.toBean(json,User.class);
        uService.updateUserAddress(loginUser);
        loginUser = uService.selectById(loginUser.getId());

        Msg msg = new Msg(true,"修改用户地址成功",loginUser);
        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
