package com.dao.impl;

import com.dao.IGoodsDao;
import com.pojo.Goods;
import com.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodsDaoImpl implements IGoodsDao {

    private Connection conn = null;
    PreparedStatement ps = null;

    public GoodsDaoImpl() {

    }

    public GoodsDaoImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * 增加商品条目
     * @param goods 商品对象
     * @return
     * @throws Exception
     */
    @Override
    public boolean addGoods(Goods goods) throws Exception {
        boolean flag = false;
        String sql = "insert into goods (g_no, g_name, g_price, g_product, g_store) " +
                "values(?,?,?,?,?)";
        this.ps = this.conn.prepareStatement(sql);
        ps.setString(1, goods.getG_no());
        ps.setString(2, goods.getG_name());
        ps.setDouble(3, goods.getG_price());
        ps.setString(4, goods.getG_product());
        ps.setInt(5, goods.getG_store());
        if (ps.executeUpdate() > 0) {
            flag = true;
        }
        closeStatement();
        return flag;
    }

    /**
     * @param id 根据str的值查询符合条件的数据(id=-1则查询全部表数据，否则查询商品id的数据)
     * @return
     * @throws Exception
     */
    @Override
    public List<Goods> findAllGoods(Integer id) throws Exception {
        List<Goods> list = new ArrayList<Goods>();
        int gid = (int) id;
        String tail = "";
        if (gid != -1) {
            tail = " where id = " + id + "";
        }
        String sql = "select * from goods" + tail;
        this.ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Goods good = null;
        while (rs.next()) {
            // 将数据库的商品信息封装成对象
            good = new Goods();
            good.setId(rs.getInt(1));
            good.setG_no(rs.getString(2));
            good.setG_name(rs.getString(3));
            good.setG_price(rs.getDouble(5));
            good.setG_product(rs.getString(4));
            good.setG_store(rs.getInt(6));
            list.add(good);
        }
        closeStatement();
        return list;
    }

    /**
     * 根据商品id来删除商品
     *
     * @param id 商品id
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteGoods(Integer id) throws Exception {
        boolean flag = false;
        String sql = "delete from goods where id = " + (int) id;
        this.ps = this.conn.prepareStatement(sql);
        if (ps.executeUpdate() > 0) {
            flag = true;
        }
        closeStatement();
        return flag;
    }

    /**
     * 更新商品信息
     *
     * @param goods 商品对象
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateGoods(Goods goods) throws Exception {

        boolean flag = false;
        String sql = "update goods set g_no = '" + goods.getG_no() +
                "', g_name = '" + goods.getG_name() + "', g_price = " +
                goods.getG_price() + ",g_store = " + goods.getG_store() +
                " where id = " + goods.getId();

        this.ps = this.conn.prepareStatement(sql);
        if (ps.executeUpdate() > 0) {
            flag = true;
        }
        closeStatement();
        return flag;
    }

    /**
     * 根据商品id来查找商品
     *
     * @param id
     * @return 总价格
     */
    public double findPriceById(Integer id) {
        String sql = "select g_price from goods where id = " + id;
        double price = 0.0;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                price = rs.getDouble(1);
            }

            closeStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return price;


    }

    /**
     * @param map 接收商品id和购买数量的键值对
     * @return
     * @throws Exception
     */
    @Override
    public double updateGoodsStore(Map<Integer, Integer> map) throws Exception {
        double total = 0.0;
        double money = 0.0;
        //boolean autoCommit = conn.getAutoCommit();
        try {

            for (Integer id : map.keySet()) {
                ResultSet rs;
                Integer count = map.get(id);
                money = findPriceById(id);
                total += money;
                String sql = "update goods set g_store = g_store - " + count + " where id = " + id;
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();

        }


        return total;

    }

    public boolean status() {
        return false;
    }

    /**
     * 关闭Statement
     */
    public void closeStatement() {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
