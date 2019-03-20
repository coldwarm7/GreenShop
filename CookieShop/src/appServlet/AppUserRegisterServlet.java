package appServlet;

import com.alibaba.fastjson.JSON;
import model.Msg;
import model.User;
import org.apache.commons.beanutils.BeanUtils;
import service.TypeService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "AppUserRegisterServlet" , urlPatterns = "/greenshop/app/user_register")
public class AppUserRegisterServlet extends HttpServlet {
    private UserService uService = new UserService();
    private TypeService tService = new TypeService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        User user = new User();
        try {
            BeanUtils.copyProperties(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Msg msg = new Msg();
        if(uService.register(user)) {
            msg.setResult(true);
            msg.setMsg("注册成功，请登录！");
            String registerJson = JSON.toJSONString(msg);
            OutputStream out = response.getOutputStream();
            out.write(registerJson.getBytes("UTF-8"));
            out.flush();
        }else {
            msg.setResult(false);
            msg.setMsg("用户名或邮箱重复，请重新填写！");
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
