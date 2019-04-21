package servlet;

import model.Goods;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "admin_goods_delete",urlPatterns = "/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Goods goods = gService.getGoodsById(id);
        File cover = new File(this.getServletContext().getRealPath("/") + goods.getCover());
        cover.delete();
        File image1 = new File(this.getServletContext().getRealPath("/") + goods.getImage1());
        System.out.println(this.getServletContext().getRealPath("/") + goods.getCover());
        image1.delete();
        File image2 = new File(this.getServletContext().getRealPath("/") + goods.getImage2());
        System.out.println(this.getServletContext().getRealPath("/") + goods.getCover());
        image2.delete();

        gService.delete(id);
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
