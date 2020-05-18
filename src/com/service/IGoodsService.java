package com.service;

import com.pojo.Goods;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IGoodsService {
    /**
     * 显示商品的信息
     * @param id 根据id值来显示商品的信息，如果尖id=0，则显示全部的商品条目，否则只显示对应的id的商品
     * @return 商品的集合
     */
    public List<Goods> showGoods(Integer id);

    /**
     * 购买商品
     * @param map 商品的id和购买数量的键值对
     * @param userId 用户id
     * @return
     */
    public boolean buyGoods(Map<Integer, Integer> map, String userId) throws SQLException;

    /**
     * 增加商品
     * @param goods 增加商品信息
     * @return
     */
    public boolean addGoods(Goods goods);

    /**
     * 删除指望id的商品
     * @param id 商品的id
     * @return
     */
    public boolean delteGoodsById(Integer id);

    /**
     * 修改商品条目
     * @param goods 依据参数的主键来修改其它字段
     * @return
     */
    public boolean updateGoods(Goods goods);
}
