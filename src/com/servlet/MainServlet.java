package com.servlet;

import com.dao.IGoodsDao;
import com.factory.GoodsDaoFactory;
import com.pojo.Goods;
import com.service.impl.GoodsServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        List<Goods> list = null;


        try {
            GoodsServiceImpl igd = GoodsDaoFactory.getGoodsDaoInstanceInstance();
            list = igd.showGoods(-1);
        }catch(Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>商品明细表</title></head>");
        out.println("<body>");
        out.println("<center><h4>商品明细表</h4>");
        out.println("<table border=\"1\" cellspacing=\"1\" cellpadding=\"2\" width=\"100%\">");
        out.println("<tr>");
        out.println("<td>序号</td>");
        out.println("<td>商品编号</td>");
        out.println("<td>商品名称</td>");
        out.println("<td>单价</td>");
        out.println("<td>原产地</td>");
        out.println("<td>库存数量</td>");
        out.println("<td>操作</td>");

        out.println("</tr>");
        for(Goods g : list) {
            int id = g.getId();
            String g_no = g.getG_no();
            String g_name = g.getG_name();
            double g_price = g.getG_price();
            String g_product = g.getG_product();
            int g_store = g.getG_store();
            out.println("<tr>");
            out.println("<td><input type=\"checkbox\" name=\"id\" value=\"" +  id + "\"></td>");
            out.println("<td>" + g_no + "</td>");
            out.println("<td>" + g_name + "</td>");
            out.println("<td>" + g_price + "</td>");
            out.println("<td>" + g_product + "</td>");
            out.println("<td>" + g_store + "</td>");
            out.println("<td><a href=\"modify.jsp?id=" + id + "\">修改" +
                    "</a>&nbsp;&nbsp<a href=\"delete.jsp?id=" +
                    id + "\" onclick=\"return confirm('确定删除此记录？')\">删除</a></td>");
            out.println("</tr>");


        }
        out.println("</table>");
        out.println("</center></body>");
        out.println("</html>");
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
