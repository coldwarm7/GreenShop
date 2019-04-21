package appServlet;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import model.Msg;
import model.User;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

@WebServlet(name = "AppChangeAddressServlet",urlPatterns = "/app/user_changeaddress")
public class AppChangeAddressServlet extends HttpServlet {

    private UserService uService = new UserService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    //json
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
//        String data = getPostData(request);
//        out.println(data);

        //data = data.substring(1, data.length()-1);
        //User loginUser = new Gson().fromJson(data, User.class);
        JSONObject json=JsonReader.receivePost(request);
        User loginUser = (User) JSONObject.toBean(json,User.class);
        uService.updateUserAddress(loginUser);
        loginUser = uService.selectById(loginUser.getId());
        List<User> users = new ArrayList<>();
        users.add(loginUser);

        Msg msg = null;
        if (loginUser != null){
            msg = new Msg(true,"修改用户地址成功",users);
        }else {
            msg = new Msg(false,"修改用户地址失败",users);
        }

        String registerJson = JSON.toJSONString(msg);
        OutputStream out = response.getOutputStream();
        out.write(registerJson.getBytes("UTF-8"));
        out.flush();
    }

    //    private static String getPostData(HttpServletRequest request) {
//        StringBuffer data = new StringBuffer();
//        String line = null;
//        BufferedReader reader = null;
//        try {
//            reader = request.getReader();
//            while (null != (line = reader.readLine()))
//                data.append(line);
//        } catch (IOException e) {
//        } finally {
//        }
//        return data.toString();
//    }
    //from-data
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        response.setContentType("application/json; charset=utf-8");
//        response.setCharacterEncoding("UTF-8");
//
//        try {
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//            upload.setHeaderEncoding("UTF-8");
//            List items = upload.parseRequest(request);
//            Map params = new HashMap();
//            for(Object object:items){
//                FileItem fileItem = (FileItem) object;
//                if (fileItem.isFormField()) {
//                    params.put(fileItem.getFieldName(), fileItem.getString("utf-8"));//如果你页面编码是utf-8的
//                }
//            }
//            //使用params.get获取参数值
//            int user_id = Integer.parseInt( (String) params.get("id"));
//            String name = (String) params.get("name");
//            String phone = (String) params.get("phone");
//            String address = (String) params.get("address");
//
//            User loginUser = new User();
//            loginUser.setId(user_id);
//            loginUser.setName(name);
//            loginUser.setPhone(phone);
//            loginUser.setAddress(address);
//            uService.updateUserAddress(loginUser);
//            loginUser = uService.selectById(loginUser.getId());
//            List<User> users = new ArrayList<>();
//            users.add(loginUser);
//
//            Msg msg = new Msg(true,"修改用户地址成功",users);
//            String registerJson = JSON.toJSONString(msg);
//            OutputStream out = response.getOutputStream();
//            out.write(registerJson.getBytes("UTF-8"));
//            out.flush();
//
//        } catch (FileUploadException e1) {
//            e1.printStackTrace();
//        }
//
//    }


}
