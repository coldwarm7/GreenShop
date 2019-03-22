package appServlet;

import com.alibaba.fastjson.JSON;
import model.Msg;
import model.User;
import service.TypeService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "AppUserLoginServlet",urlPatterns = "/app/user_login")
public class AppUserLoginServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        Msg msg = new Msg();

        String ue = request.getParameter("username");
        String password = request.getParameter("password");
        User user = uService.login(ue, password);
        if(user==null) {
            msg.setCode(false);
            msg.setMsg("用户名、邮箱或者密码错误，请重新登录！");
            String registerJson = JSON.toJSONString(msg);
            OutputStream out = response.getOutputStream();
            out.write(registerJson.getBytes("UTF-8"));
            out.flush();
        }else {
            msg.setData(user);
            msg.setMsg("登陆成功");
            msg.setCode(true);
            String registerJson = JSON.toJSONString(msg);
            OutputStream out = response.getOutputStream();
            out.write(registerJson.getBytes("UTF-8"));
            out.flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
