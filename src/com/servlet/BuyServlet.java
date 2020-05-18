package com.servlet;

import com.factory.GoodsDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        // 商品id
        Integer i_id = new Integer(Integer.parseInt(id));
        // 购物的数量，暂时只购一件
        Integer num = new Integer(1);
        boolean flag = false;
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        // 结合成map做为参数
        map.put(i_id, num);
        String u_id = (String)request.getSession().getAttribute("uname");
        // 购买商品
        flag = GoodsDaoFactory.getGoodsDaoInstanceInstance().buyGoods(map,u_id);
        if(flag){
            request.getSession().setAttribute("state", "购买成功");
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        }else{
            request.getSession().setAttribute("err", "购买失败");
            request.getRequestDispatcher("error.jsp")
                    .forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
