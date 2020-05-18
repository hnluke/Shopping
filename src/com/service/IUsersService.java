package com.service;

import com.pojo.Users;


public interface IUsersService {
    /**
     * 验证用户合法性
     * @param id 用户账号
     * @param pwd 用户密码
     * @return
     */
    public boolean verify(String id, String pwd);

    /**
     * 查询账户余额
     * @param id 用户账号
     * @return
     */
    public Double queryAccountById(String id);
}



