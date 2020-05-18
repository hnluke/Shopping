package com.service.impl;

import com.dao.impl.GoodsDaoImpl;
import com.dao.IGoodsDao;
import com.dao.impl.UsersDaoImpl;
import com.db.DBConnection;
import com.pojo.Goods;
import com.pojo.Users;
import com.service.IGoodsService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GoodsServiceImpl implements IGoodsService {
    private Connection conn = null;
    private GoodsDaoImpl goodsDaoImpl = null;
    private UsersDaoImpl usersDaoImpl = null;
    public GoodsServiceImpl() throws Exception{
        this.conn = DBConnection.getConnect("c3p0");
        this.goodsDaoImpl = new GoodsDaoImpl(conn);
        this.usersDaoImpl = new UsersDaoImpl(conn);
    }

    /**
     *
     * @param id 根据id值来显示商品的信息，如果尖id=0，则显示全部的商品条目，否则只显示对应的id的商品
     * @return
     */
    @Override
    public List<Goods> showGoods(Integer id) {
        List<Goods> list = new ArrayList<Goods>();
        try {
            list = goodsDaoImpl.findAllGoods(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     *
     * @param map 商品的id和购买数量的键值对
     * @param userId 用户id
     * @return
     */

    @Override
    public boolean buyGoods(Map<Integer, Integer> map, String userId) {
        boolean flag = false;
        double money = 0.0;

        try {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            money = goodsDaoImpl.updateGoodsStore(map);
            flag = usersDaoImpl.updateAccountById(userId, money);
            flag = true;
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            try {
                conn.rollback();
            }catch(SQLException e2) {
                e2.printStackTrace();
            }


        }
        return flag;
    }

    /**
     *
     * @param goods 增加商品信息
     * @return
     */
    @Override
    public boolean addGoods(Goods goods){
        boolean flag = false;
        try {
            flag = goodsDaoImpl.addGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     * @param id 商品的id
     * @return
     */
    @Override
    public boolean delteGoodsById(Integer id) {
        boolean flag = false;
        try {
            flag = goodsDaoImpl.deleteGoods(id);

        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        boolean flag = false;
        try {
            flag = goodsDaoImpl.updateGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void closeConnect() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
