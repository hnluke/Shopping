package com.servlet;

import com.factory.GoodsDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = (String)request.getParameter("unames");
        String pwd = (String)request.getParameter("pwd");

        boolean flag = false;
        // 设置错误提示err 和 操作结果提示state
        request.getSession().setAttribute("err", "");
        request.getSession().setAttribute("state", "    ");
        try {
            flag = GoodsDaoFactory.getUsersServiceInstance().verify(user, pwd);
            if(flag)
            {
                request.getSession().setAttribute("uname", user);
                response.sendRedirect(request.getContextPath() + "/main.jsp");
            } else {
                request.getSession().setAttribute("err", "登录失败");
                request.getRequestDispatcher("Login.jsp")
                        .forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
