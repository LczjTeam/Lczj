package jx.lczj.viewmodel;

import jx.lczj.model.T_attachment;
import jx.lczj.model.T_category;

import java.util.List;

public class T_goodsHot {

    private String name;
    private int price;
    private T_category t_categories;
    private T_attachment t_attachments;
    private List<T_category> t_categoryList;

    public List<T_category> getT_categoryList() {
        return t_categoryList;
    }

    public void setT_categoryList(List<T_category> t_categoryList) {
        this.t_categoryList = t_categoryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public T_attachment getT_attachments() {
        return t_attachments;
    }

    public void setT_attachments(T_attachment t_attachments) {
        this.t_attachments = t_attachments;
    }

    public T_category getT_categories() {
        return t_categories;
    }

    public void setT_categories(T_category t_categories) {
        this.t_categories = t_categories;
    }

    @Override
    public String toString() {
        return "T_goodsHot{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", t_categories=" + t_categories +
                ", t_attachments=" + t_attachments +
                ", t_categoryList=" + t_categoryList +
                '}';
    }
}