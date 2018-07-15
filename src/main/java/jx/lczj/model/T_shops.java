package jx.lczj.model;

import java.io.Serializable;

public class T_shops implements Serializable{
    private String shop;            //分店号
    private String name;            //店名
    private String address;         //地址
    private String phone;           //电话
    private float pos_x;            //经度
    private float Pos_y;            //纬度
    private int orders;             //排序

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getPos_x() {
        return pos_x;
    }

    public void setPos_x(float pos_x) {
        this.pos_x = pos_x;
    }

    public float getPos_y() {
        return Pos_y;
    }

    public void setPos_y(float pos_y) {
        Pos_y = pos_y;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "T_shops{" +
                "shop='" + shop + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", pos_x=" + pos_x +
                ", Pos_y=" + Pos_y +
                ", orders=" + orders +
                '}';
    }
}
