package com.epam.lab.servlet;

import com.epam.lab.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebFilter(urlPatterns = { "/*" })
public class SecurityFilter implements Filter {
    HashMap<String, List<Integer>> map;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        map = new HashMap<>();
        List<Integer> list =new ArrayList<>();
        list =new ArrayList<>();
        list.add(3);
        map.put("/cards",list);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        boolean authorized=false;
        int role;
        UserDto user;
        String path;
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        path=req.getServletPath();
        req.setCharacterEncoding("UTF-8");
        final HttpSession session = req.getSession();
        if (session.getAttribute("user")!=null) {
            authorized=true; user=(UserDto) session.getAttribute("user");
            role=user.getRole();
        } else {
            role=3;}
        System.out.println("role="+role);
        List<Integer> list=new ArrayList<>();
        list=map.get(path);
        System.out.println("path is" +path);
        if ((list!=null)&&(authorized==false))
        {
            if (list.contains(role)) {chain.doFilter(request,response);}
            else {
                req.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }
        if ((list!=null)&&(authorized==true))
        {
            if (list.contains(role)) {
                System.out.println("auth true list true");chain.doFilter(request,response);}
            else {System.out.println("auth true list false");
                req.getRequestDispatcher("error.jsp").forward(request,response);
            }
        }

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
