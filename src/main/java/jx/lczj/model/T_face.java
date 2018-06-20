package jx.lczj.model;

import java.io.Serializable;

public class T_face implements Serializable{
    private int face;               //脸型编号
    private String name;            //脸型名称
    private String photo;           //脸型图片

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "T_face{" +
                "face=" + face +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
