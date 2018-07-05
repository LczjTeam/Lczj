package jx.lczj.model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/12.
 */

/**
 * 品牌
 */
public class T_brand implements Serializable{
    private int brand;      //编号
    private String name;    //名称
    private String company; //制造商
    private int type;       //眼镜类别

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "T_brand{" +
                "brand=" + brand +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", _class=" + type +
                '}';
    }
}
