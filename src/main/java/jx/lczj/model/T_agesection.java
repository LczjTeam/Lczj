package jx.lczj.model;

import java.io.Serializable;

public class T_agesection implements Serializable{
    private int agesection;     //年龄段编号
    private String name;        //年龄段名称
    private int minage;         //最小年龄
    private int maxage;         //最大年龄

    public int getAgesection() {
        return agesection;
    }

    public void setAgesection(int agesection) {
        this.agesection = agesection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinage() {
        return minage;
    }

    public void setMinage(int minage) {
        this.minage = minage;
    }

    public int getMaxage() {
        return maxage;
    }

    public void setMaxage(int maxage) {
        this.maxage = maxage;
    }

    @Override
    public String toString() {
        return "T_agesection{" +
                "agesection=" + agesection +
                ", name='" + name + '\'' +
                ", minage=" + minage +
                ", maxage=" + maxage +
                '}';
    }
}
