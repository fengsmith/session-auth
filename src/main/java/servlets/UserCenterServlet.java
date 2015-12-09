package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shfq on 2015/12/8.
 */
public class UserCenterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginName = (String) req.getSession().getAttribute("loginInfo");
        resp.setHeader("Content-Type", "application/json;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String jsonString =
                "{\"result\" : true, \"message\" : \"" + "用户" + loginName + "您已登录" + "\"}";

        printWriter.print(jsonString);
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
