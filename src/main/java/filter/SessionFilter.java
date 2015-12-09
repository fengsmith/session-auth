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
        blackList.add("/userCenter");
    }
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!isLogedInUser(request)) {
            if (needToFilter(request.getServletPath())) {
                if (isAjaxAccess(request)) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                    httpServletResponse.addHeader("loginFlag", "false");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.print("{}");
                    printWriter.flush();
                    printWriter.close();
                    return;
                } else {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                    ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FOUND);
                    httpServletResponse.setHeader("Location", "preLogin");
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

    private boolean isAjaxAccess(HttpServletRequest request) {
        return isNotBlank(request.getHeader("x-requested-with"));
    }
}
