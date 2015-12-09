package filter;

import constant.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shfq on 2015/12/8.
 */
public class SessionFilter implements Filter {
    private final static Set<String> blackList = new HashSet<String>();

    static {
        blackList.add("/preUserCenter");
    }
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!isLogedInUser(request)) {
            if (needToFilter(request.getServletPath())) {
                if (true) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                    httpServletResponse.setHeader("Content-Type", "application/json;charset=UTF-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    String jsonString =
                            "{\"result\" : false, \"errorMessage\" : \"APP ÔÝÎ´ÉÏÏß£¬¾´ÇëÆÚ´ý\", \"errorCode\" : \"sessionExpired\"}";

                    printWriter.print(jsonString);
                    printWriter.flush();
                    printWriter.close();
                    return;
                }

            }

        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {

    }

    private boolean isLogedInUser(HttpServletRequest request) {
        String loginInfo = (String) request.getSession().getAttribute(Constant.LOGIN_INFO);
        return isNotBlank(loginInfo);
    }

    private boolean isBlank(String s) {
        if (s == null || s.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    private static boolean needToFilter(String servletPath) {
        return blackList.contains(servletPath);
    }
}
