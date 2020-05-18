package com.dao;

import com.pojo.Users;

public interface IUsersDao {
    /**
     * 根据Id查找用户
     * @param id 用户的id值
     * @return Users
     */
    public Users findById(String id);

    /**
     * 更新账户余额
     * @param id 用户账号
     * @param money 购物金额
     * @return
     */
    public boolean updateAccountById(String id, Double money);

    /**
     * 查询账户余额
     * @param id 账号
     * @return
     */
    public Double queryAccountById(String id);
}
