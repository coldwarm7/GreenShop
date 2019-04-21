package servlet;

import model.Type;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "user_center",urlPatterns = "/user_center")
public class UserCenterServlet extends HttpServlet {
    private TypeService tService = new TypeService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Type> list= tService.GetAllType();
        request.setAttribute("list", list);
        request.getRequestDispatcher("user_center.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }
}
