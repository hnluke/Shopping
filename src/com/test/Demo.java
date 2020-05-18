package com.test;


import com.factory.GoodsDaoFactory;
import com.service.IGoodsService;
import com.service.impl.GoodsServiceImpl;

public class Demo {
    public static void main(String[] args) {
        GoodsServiceImpl iGoodsService = (GoodsServiceImpl)GoodsDaoFactory.getGoodsDaoInstanceInstance();
        iGoodsService.closeConnect();


    }

}
