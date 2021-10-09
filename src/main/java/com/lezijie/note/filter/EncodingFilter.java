package com.lezijie.note.filter;


import cn.hutool.core.util.StrUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //处理POST请求，GET请求不受影响
        request.setCharacterEncoding("UTF-8");

        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            String serverInfo = request.getServletContext().getServerInfo();
            //todo: serverInfo具体输出版本格式
            String version = serverInfo.substring(serverInfo.lastIndexOf("/") + 1, serverInfo.indexOf("."));
            if (version != null && Integer.parseInt(version) < 8) {
                MyWrapper myRequest = new MyWrapper(request);
                filterChain.doFilter(myRequest,response);
                return;
            }
        }
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    class MyWrapper extends HttpServletRequestWrapper {

        private HttpServletRequest request;

        public MyWrapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            String value = request.getParameter(name);
            if (StrUtil.isBlank(value)) {
                return value;
            }
            try {
                value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return value;
        }
    }
}
