<%--
  Created by IntelliJ IDEA.
  User: Luke
  Date: 2020/3/5
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.pojo.Goods" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.service.impl.GoodsServiceImpl" %>
<%@ page import="com.factory.GoodsDaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getSession().getAttribute("uname") == null) {
        response.sendRedirect("Login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品明细表</title>
    <script src="jquery-3.4.1.js"></script>
    <script>
        function ope() {
            //alert($("#operate").val());
            if ($("#operate").val() == "新增") {
                var form1 = document.getElementById("form_main");
                if (confirm("确实要新增吗", "新增")) {
                    form1.action = "${pageContext.request.contextPath}/InsertServlet?act=1";
                    form1.submit();
                }
            } else if ($("#operate").val() == "修改") {
                var form2 = document.getElementById("form_main");
                if (confirm("确实要修改吗", "修改")) {
                    form2.action = "${pageContext.request.contextPath}/InsertServlet?act=2";
                    form2.submit();
                }
            }

        }

        function logouts() {
            location.href = "${pageContext.request.contextPath}/Login.jsp";
            window.sessionStorage.clear();
        }

        function getData(id) {

            $.post("${pageContext.request.contextPath}/AjaxServlet", {"goodId": id}, function (callback) {
                //alert(callback);
                var jsonObj = JSON.parse(callback);
                $("#g_id").val(jsonObj[0].id);
                $("#g_no").val(jsonObj[0].g_no);
                $("#g_name").val(jsonObj[0].g_name);
                $("#g_price").val(jsonObj[0].g_price);
                $("#g_product").val(jsonObj[0].g_product);
                $("#g_store").val(jsonObj[0].g_store);
                $("#operate").val("修改");
            });
        }
    </script>
</head>
<%
    Connection conn = null;
    List<Goods> list = null;
    GoodsServiceImpl gsi = null;
    String unames = "";
    try {
        gsi = GoodsDaoFactory.getGoodsDaoInstanceInstance();
        list = gsi.showGoods(-1);
        unames = (String) request.getSession().getAttribute("uname");
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<body style="text-align: center">
<h4>商品明细表</h4>
<hr>
<form action="" id="form_main" method="post">
    <table border="0" cellspacing="2" width="95%">
        <tr style="text-align: left">

            <td><input hidden="true" id="g_id" name="g_id">
                商品编号&nbsp;

                <input type="text" id="g_no" name="g_no" style="width: 50px">&nbsp;
                商品名称&nbsp;
                <input type="text" id="g_name" name="g_name">&nbsp;
                单价&nbsp;
                <input type="text" id="g_price" name="g_price" style="width: 40px">&nbsp;
                原产地&nbsp;
                <input type="text" id="g_product" name="g_product">&nbsp;
                库存数量&nbsp;
                <input type="text" id="g_store" name="g_store" style="width: 40px">&nbsp;

                <input type="button" id="operate" value="新增" onclick="ope()"/>
                &nbsp;&nbsp;&nbsp;
                登录用户：${uname}&nbsp;&nbsp;
                账户余额：<%=GoodsDaoFactory.getUsersServiceInstance().queryAccountById(unames)%>
                &nbsp;&nbsp;<label style="color: darkgreen">${state}</label>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" id="logout" value="注销" onclick="logouts()"/>

            </td>

        </tr>

    </table>
</form>

<hr>
<table border="1" cellspacing="2" width="100%">
    <tr>
        <td>选择</td>
        <td>商品编号</td>
        <td>商品名称</td>
        <td>单价</td>
        <td>原产地</td>
        <td>库存数量</td>
        <td>操作</td>
    </tr>
    <%
        for (Goods g : list) {
            int id = g.getId();
            String g_no = g.getG_no();
            String g_name = g.getG_name();
            double g_price = g.getG_price();
            String g_product = g.getG_product();
            int g_store = g.getG_store();
    %>
    <tr>
        <td><input type="checkbox" name="id" value="<%=id%>"></td>
        <td><%=g_no%>
        </td>
        <td><%=g_name%>
        </td>
        <td><%=g_price%>
        </td>
        <td><%=g_product%>
        </td>
        <td><%=g_store%>
        </td>
        <td><a id="abc" href="javascript:void(0)" onclick="getData(<%=id%>);">修改</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/DeleteServlet?id=<%=id%>"
               onclick="return confirm('确实要删除吗?')">删除</a>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/BuyServlet?id=<%=id%>"
               onclick="return confirm('确实要购买吗?')">购买</a>
        </td>
    </tr>
    <%
        session.setAttribute("state", "");}

    %>
</table>

</body>

<%
    gsi.closeConnect();
%>
</html>
