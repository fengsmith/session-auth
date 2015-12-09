package servlets;

import constant.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shfq on 2015/12/8.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> loginAccounts = new HashMap<String, String>();
        loginAccounts.put("user", "111111");

        String loginName = req.getParameter("loginName");
        String password = req.getParameter("password");
        if (loginAccounts.containsKey(loginName) && loginAccounts.get(loginName).equals(password)) {
            req.getSession().setAttribute(Constant.LOGIN_INFO, loginName);
            req.getRequestDispatcher("/WEB-INF/pages/loginSuccess.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/pages/loginFail.jsp").forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
