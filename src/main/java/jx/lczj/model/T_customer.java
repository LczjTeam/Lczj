package jx.lczj.model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/10.
 */

/**
 * 会员
 */
public class T_customer implements Serializable{
    private String vip;  //   会员编号
    private String name;   //   姓名
    private String phone;   //  手机号
    private String sex;     //  性别
    private String pwd ;   //   密码 默认 '123456'
    private String birthday;   //   出生年月
    private String face;//  头像
    private int prize;

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "T_customer{" +
                "vip='" + vip + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", pwd='" + pwd + '\'' +
                ", birthday='" + birthday + '\'' +
                ", face='" + face + '\'' +
                ", prize=" + prize +
                '}';
    }
}
