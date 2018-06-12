package jx.lczj.model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/12.
 */

/**
 * 眼镜信息
 */
public class T_goods implements Serializable {

    private  String  goods;     //编号  '可以采取固定编码形式，编写统一长度';
    private  int  category;     //类别
    private  int  brand;        //品牌
    private  String  name;      //名称
    private  String  model;     //型号
    private  int  width;        //镜面宽 '单位为mm';
    private  int  height;       //镜面高'单位为mm';
    private  int  space;        //鼻尖距离
    private  int  length;       //镜腿长
    private  int  max_width;    //总宽度

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMax_width() {
        return max_width;
    }

    public void setMax_width(int max_width) {
        this.max_width = max_width;
    }

    @Override
    public String toString() {
        return "T_goods{" +
                "goods='" + goods + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", space=" + space +
                ", length=" + length +
                ", max_width=" + max_width +
                '}';
    }
}
