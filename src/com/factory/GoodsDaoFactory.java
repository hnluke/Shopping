package com.factory;

import com.dao.IGoodsDao;
import com.service.IGoodsService;
import com.service.IUsersService;
import com.service.impl.GoodsServiceImpl;
import com.service.impl.UsersServiceImpl;

public class GoodsDaoFactory {
    public static GoodsServiceImpl getGoodsDaoInstanceInstance() {
        GoodsServiceImpl gsi = null;
        try {
            gsi =  new GoodsServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gsi;
    }

    public static UsersServiceImpl getUsersServiceInstance() {
        UsersServiceImpl usi = null;
        try {
            usi = new UsersServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usi;

    }
}
