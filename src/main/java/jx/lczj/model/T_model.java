package jx.lczj.model;

import java.io.Serializable;

public class T_model implements Serializable{
    private int model;              //模特编号
    private int face;               //脸型编号
    private String name;            //模特名称
    private int age;                //适用年龄
    private String sex;             //适用年龄
    private String photo;           //模特照片

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "T_model{" +
                "model=" + model +
                ", face=" + face +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

}
