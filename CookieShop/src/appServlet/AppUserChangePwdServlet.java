package appServlet;

import com.alibaba.fastjson.JSON;
import model.Msg;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "AppUserChangePwdServlet",urlPatterns = "/greenshop/app/user_changepwd")
public class AppUserChangePwdServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        Msg msg = new Msg();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPwd = request.getParameter("newPassword");

        User u = uService.login(username,password);
        if (u != null){
            u.setPassword(newPwd);
            uService.updatePwd(u);
            msg.setResult(true);
            msg.setMsg("修改成功！");
            String registerJson = JSON.toJSONString(msg);
            OutputStream out = response.getOutputStream();
            out.write(registerJson.getBytes("UTF-8"));
            out.flush();

        }else {
            msg.setResult(false);
            msg.setMsg("修改失败，原密码不正确，你再想想！");
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
