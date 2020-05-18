package com.servlet;

import com.factory.GoodsDaoFactory;
import com.pojo.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据operate的值来决定是插入还是修改
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operate = (String)request.getParameter("act");
        String g_id = (String)request.getParameter("g_id");
        if(g_id == null || "".equals(g_id)) {
            g_id = "0";
        }
        String g_no = request.getParameter("g_no");
        String g_name = request.getParameter("g_name");
        String g_price = request.getParameter("g_price");
        String g_product = request.getParameter("g_product");
        String g_store = request.getParameter("g_store");
        Goods goods = new Goods();
        boolean flag = false;
        goods.setId(Integer.parseInt(g_id));
        goods.setG_no(g_no);
        goods.setG_name(g_name);
        goods.setG_price(Double.parseDouble(g_price));
        goods.setG_product(g_product);
        goods.setG_store(Integer.parseInt(g_store));
        if("1".equals(operate)) {
            // operate值为1代表增加商品
            flag = GoodsDaoFactory.getGoodsDaoInstanceInstance().addGoods(goods);
            System.out.println(g_id);
            if(flag) {
                request.getSession().setAttribute("state", "新增成功");
                response.sendRedirect(request.getContextPath() + "/main.jsp");
            }else
            {
                System.out.println("Error");
            }
        }else if("2".equals(operate)) {
            // operate值为2代表修改商品
            flag = GoodsDaoFactory.getGoodsDaoInstanceInstance().updateGoods(goods);
            if(flag) {
                request.getSession().setAttribute("state", "修改成功");
                response.sendRedirect(request.getContextPath() + "/main.jsp");
            }else{
                System.out.println("Error");
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
