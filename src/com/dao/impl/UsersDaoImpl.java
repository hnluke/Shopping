package com.dao.impl;

import com.dao.IUsersDao;
import com.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements IUsersDao {
    Connection conn = null;
    PreparedStatement ps = null;

    public UsersDaoImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Users findById(String id) {
        Users users = null;
        String sql = "select * from users where u_id = '" + id + "'";
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                users = new Users();
                users.setU_id(rs.getString(1));
                users.setU_name(rs.getString(2));
                users.setU_pwd(rs.getString(3));
                users.setU_priority(rs.getString(4));
                users.setU_account(rs.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return users;
    }

    @Override
    public boolean updateAccountById(String id, Double money) {
        boolean flag = false;
        String sql = "update users set u_account = u_account -" + money +
                " where u_id = '" + id + "'";
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeStatement();
        return flag;
    }

    @Override
    public Double queryAccountById(String id) {
        double account = 0.0;
        String sql = "select u_account from users where u_id = '" + id + "'";
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                account = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }


    public void closeStatement() {
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
