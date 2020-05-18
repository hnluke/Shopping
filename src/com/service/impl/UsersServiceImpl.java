package com.service.impl;

import com.dao.IUsersDao;
import com.dao.impl.UsersDaoImpl;
import com.db.DBConnection;
import com.pojo.Users;
import com.service.IUsersService;

import java.sql.*;
public class UsersServiceImpl implements IUsersService {
    UsersDaoImpl usersDaoImpl = null;
    Connection conn = null;

    public UsersServiceImpl() {
        conn = DBConnection.getConnect("c3p0");
        this.usersDaoImpl = new UsersDaoImpl(conn);
    }

    @Override
    public boolean verify(String id, String pwd) {
        boolean flag = false;
        Users users = usersDaoImpl.findById(id);
        if(users != null) {
            if(users.getU_pwd().equals(pwd)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Double queryAccountById(String id) {
        if("".equals(id) || id == null)
            return 0.0;
        return usersDaoImpl.queryAccountById(id);

    }
}
