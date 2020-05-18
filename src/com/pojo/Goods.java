package com.pojo;

import java.io.Serializable;

public class Goods implements Serializable {
    public final static long serialVersionUID = 100L;
    private Integer id;
    private String g_no;
    private String g_name;
    private String g_product;
    private Double g_price;
    private Integer g_store;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getG_no() {
        return g_no;
    }

    public void setG_no(String g_no) {
        this.g_no = g_no;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_product() {
        return g_product;
    }

    public void setG_product(String g_product) {
        this.g_product = g_product;
    }

    public double getG_price() {
        return g_price;
    }

    public void setG_price(double g_price) {
        this.g_price = g_price;
    }

    public Integer getG_store() {
        return g_store;
    }

    public void setG_store(int g_store) {
        this.g_store = g_store;
    }
}
