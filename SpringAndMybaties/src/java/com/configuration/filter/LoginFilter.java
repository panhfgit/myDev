/**
 * $Id: LoginFilter.java,v 1.0 2018/3/3 22:20 pan Exp $
 * <p/>
 * Copyright 2016 Asiainfo Technologies(China),Inc. All rights reserved.
 */
package com.configuration.filter;/**
 * Created by pan on 2018/3/3.
 */

/**
 * @author pan
 * @version $Id: LoginFilter.java,v 1.1 2018/3/3 22:20 pan Exp $
 * Created on 2018/3/3 22:20
 */


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

    private FilterConfig config;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();

        String noLoginPaths = config.getInitParameter("noLoginPaths");

        String charset = config.getInitParameter("charset");
        if(charset==null){
            charset = "UTF-8";
        }
        request.setCharacterEncoding(charset);

        if(noLoginPaths!=null){
            String[] strArray = noLoginPaths.split(";");
            for (int i = 0; i < strArray.length; i++) {

                if(strArray[i]==null || "".equals(strArray[i]))
                    continue;
                if(request.getRequestURI().indexOf(strArray[i])!=-1 ){
                    arg2.doFilter(arg0, arg1);
                    return;
                }
            }

        }

        if(session.getAttribute("userId")!=null){
            arg2.doFilter(arg0, arg1);
        }else{
            response.sendRedirect("/page/login.html");
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        config = arg0;
    }

}
