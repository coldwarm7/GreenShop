package appServlet;


/*
    json demo
 */
import com.alibaba.fastjson.JSON;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "json_demo",urlPatterns = "/json")
public class JsonServlet extends HttpServlet {
    private User buildUserDO(){
        User user = new User();
        user.setName("张三");
        user.setPassword("123");
        return user;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String userJson = JSON.toJSONString(buildUserDO());
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
