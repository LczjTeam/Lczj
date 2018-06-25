package jx.lczj.viewmodel;

import jx.lczj.model.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 14260 on 2018/6/25.
 */
public class GoodsVo implements Serializable{

    private T_goods t_goods;
    private T_brand t_brand;
    private T_category t_category;
    private List<T_color> t_colors;
    private List<T_face> t_faces;
    private List<T_occasion> t_occasions;
    private List<T_agesection> t_agesections;
    private List<T_attachment> t_attachments;

    public T_goods getT_goods() {
        return t_goods;
    }

    public void setT_goods(T_goods t_goods) {
        this.t_goods = t_goods;
    }

    public T_brand getT_brand() {
        return t_brand;
    }

    public void setT_brand(T_brand t_brand) {
        this.t_brand = t_brand;
    }

    public T_category getT_category() {
        return t_category;
    }

    public void setT_category(T_category t_category) {
        this.t_category = t_category;
    }

    public List<T_color> getT_colors() {
        return t_colors;
    }

    public void setT_colors(List<T_color> t_colors) {
        this.t_colors = t_colors;
    }

    public List<T_face> getT_faces() {
        return t_faces;
    }

    public void setT_faces(List<T_face> t_faces) {
        this.t_faces = t_faces;
    }

    public List<T_occasion> getT_occasions() {
        return t_occasions;
    }

    public void setT_occasions(List<T_occasion> t_occasions) {
        this.t_occasions = t_occasions;
    }

    public List<T_agesection> getT_agesections() {
        return t_agesections;
    }

    public void setT_agesections(List<T_agesection> t_agesections) {
        this.t_agesections = t_agesections;
    }

    public List<T_attachment> getT_attachments() {
        return t_attachments;
    }

    public void setT_attachments(List<T_attachment> t_attachments) {
        this.t_attachments = t_attachments;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "t_goods=" + t_goods +
                ", t_brand=" + t_brand +
                ", t_category=" + t_category +
                ", t_colors=" + t_colors +
                ", t_faces=" + t_faces +
                ", t_occasions=" + t_occasions +
                ", t_agesections=" + t_agesections +
                ", t_attachments=" + t_attachments +
                '}';
    }
}
