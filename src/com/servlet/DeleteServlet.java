package com.servlet;

import com.factory.GoodsDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除商品的Servlet程序
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean flag = false;
        String id = request.getParameter("id");
        flag = GoodsDaoFactory.getGoodsDaoInstanceInstance().delteGoodsById(Integer.parseInt(id));
        if(flag) {
            request.getSession().setAttribute("state", "删除成功");
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        }else
        {
            request.getSession().setAttribute("err", "删除失败");
            request.getRequestDispatcher("error.jsp")
            .forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
