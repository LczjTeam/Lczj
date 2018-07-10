package jx.lczj.model;

/**
 * Created by WuLinZhi on 2018-07-09.
 */
public class T_mywear {
    private String mywear; //主属性非空
    private int occasion;  //场景
    private String customer; //顾客
    private int color; //颜色
    private int face;//脸型
    private String goods;//镜框
    private int pupil;//瞳孔
    private String sex;//性别
    private String age;//性别
    private String selfphoto;//自拍照
    private String showphoto;//试戴照
    private int deal;//是否成交
    private int iscart;//是否添加购物车

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMywear() {
        return mywear;
    }

    public void setMywear(String mywear) {
        this.mywear = mywear;
    }

    public int getOccasion() {
        return occasion;
    }

    public void setOccasion(int occasion) {
        this.occasion = occasion;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public int getPupil() {
        return pupil;
    }

    public void setPupil(int pupil) {
        this.pupil = pupil;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSelfphoto() {
        return selfphoto;
    }

    public void setSelfphoto(String selfphoto) {
        this.selfphoto = selfphoto;
    }

    public String getShowphoto() {
        return showphoto;
    }

    public void setShowphoto(String showphoto) {
        this.showphoto = showphoto;
    }

    public int getDeal() {
        return deal;
    }

    public void setDeal(int deal) {
        this.deal = deal;
    }

    public int getIscart() {
        return iscart;
    }

    public void setIscart(int iscart) {
        this.iscart = iscart;
    }


    @Override
    public String toString() {
        return "T_mywear{" +
                "mywear='" + mywear + '\'' +
                ", occasion=" + occasion +
                ", customer='" + customer + '\'' +
                ", color=" + color +
                ", face=" + face +
                ", goods='" + goods + '\'' +
                ", pupil=" + pupil +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", selfphoto='" + selfphoto + '\'' +
                ", showphoto='" + showphoto + '\'' +
                ", deal=" + deal +
                ", iscart=" + iscart +
                '}';
    }
}
