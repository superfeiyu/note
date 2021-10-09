package com.lezijie.note.filter;


import com.lezijie.note.po.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //放行登录
        String path = request.getRequestURI();
        if (path.contains("/login.jsp")) {
            filterChain.doFilter(request, response);
            return;
        }

        //放行静态资源
        if (path.contains("/statics")) {
            filterChain.doFilter(request, response);
            return;
        }

        //指定行为放行
        if (path.contains("/user")) {
            String actionName = request.getParameter("actionName");
            if ("login".equals(actionName)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        //登录状态，放行(判断session作用域中是否存在user对象；存在则放行，不存在，则拦截跳转到登录页面)
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            filterChain.doFilter(request, response);
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String value=cookie.getValue();
                String[] val=value.split("-");
                String userName=val[0];
                String userPwd=val[1];
                String url="user?actionName=login&userName="+userName+"&userPwd="+userPwd;
                request.getRequestDispatcher(url).forward(request,response);
                return;
            }
        }
        response.sendRedirect("login.jsp");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
