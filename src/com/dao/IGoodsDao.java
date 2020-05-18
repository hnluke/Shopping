package com.dao;

import com.pojo.Goods;

import java.util.List;
import java.util.Map;

public interface IGoodsDao {
    /**
     * 增加商品条目1
     * @param good 商品对象
     * @return 成功会返回true,否则返回false
     * @throws Exception
     */
    public boolean addGoods(Goods good) throws Exception;

    /**
     * 查询数据库
     * @param id 根据str的值查询符合条件的数据(id=-1则查询全部表数据，否则查询商品id的数据)
     * @return 返回集合List<Goods>
     * @throws Exception
     */
    public List<Goods> findAllGoods(Integer id) throws Exception;

    /**
     * 删除商品条目
     * @param id
     * @return 删除成功返回true
     * @throws Exception
     */
    public boolean deleteGoods(Integer id) throws Exception;

    /**
     * 更新商品的属性
     * @param goods
     * @return
     * @throws Exception
     */
    public boolean updateGoods(Goods goods) throws Exception;

    /**
     * 更新库存
     * @param map 接收商品id和购买数量的键值对
     * @return
     * @throws Exception
     */
    public double updateGoodsStore(Map<Integer, Integer> map) throws Exception;


}
