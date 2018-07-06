package jx.lczj.viewmodel;

import jx.lczj.dao.MaskDao;
import jx.lczj.model.*;

import java.util.List;

public class EyeglassVo {

    private T_eyeglass t_eyeglass;
    private T_category t_category;
    private T_efficacy t_efficacy;
    private T_brand t_brand;
    private T_mask t_mask;
    private T_style t_style;
    private List<T_attachment> t_attachments;

    public T_eyeglass getT_eyeglass() {
        return t_eyeglass;
    }

    public void setT_eyeglass(T_eyeglass t_eyeglass) {
        this.t_eyeglass = t_eyeglass;
    }

    public T_category getT_category() {
        return t_category;
    }

    public void setT_category(T_category t_category) {
        this.t_category = t_category;
    }

    public T_efficacy getT_efficacy() {
        return t_efficacy;
    }

    public void setT_efficacy(T_efficacy t_efficacy) {
        this.t_efficacy = t_efficacy;
    }

    public T_brand getT_brand() {
        return t_brand;
    }

    public void setT_brand(T_brand t_brand) {
        this.t_brand = t_brand;
    }

    public T_mask getT_mask() {
        return t_mask;
    }

    public void setT_mask(T_mask t_mask) {
        this.t_mask = t_mask;
    }

    public T_style getT_style() {
        return t_style;
    }

    public void setT_style(T_style t_style) {
        this.t_style = t_style;
    }

    public List<T_attachment> getT_attachments() {
        return t_attachments;
    }

    public void setT_attachments(List<T_attachment> t_attachments) {
        this.t_attachments = t_attachments;
    }

    @Override
    public String toString() {
        return "EyeglassVo{" +
                "t_eyeglass=" + t_eyeglass +
                ", t_category=" + t_category +
                ", t_efficacy=" + t_efficacy +
                ", t_brand=" + t_brand +
                ", t_mask=" + t_mask +
                ", t_style=" + t_style +
                ", t_attachments=" + t_attachments +
                '}';
    }
}
