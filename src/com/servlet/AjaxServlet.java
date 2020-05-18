package com.servlet;

import com.factory.GoodsDaoFactory;
import com.google.gson.Gson;
import com.pojo.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 从服务端取出要修改的商品信息，然后传回页面
 */
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String g_id = (String)request.getParameter("goodId");
        String sql = "select * from goods where g_id = " + g_id;
        List<Goods> list = new ArrayList<Goods>();
        Gson gson = new Gson();
        String JSON_TXT = "";
        try {
            list = GoodsDaoFactory.getGoodsDaoInstanceInstance().showGoods(Integer.parseInt(g_id));
            JSON_TXT = gson.toJson(list);
            PrintWriter out = response.getWriter();
            out.write(JSON_TXT);
        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
