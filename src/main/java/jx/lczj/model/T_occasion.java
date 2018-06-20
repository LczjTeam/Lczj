package jx.lczj.model;

import java.io.Serializable;

public class T_occasion implements Serializable{
    private int occasion;                 //场景编号
    private String name;                  //场景名称

    public int getOccasion() {
        return occasion;
    }

    public void setOccasion(int occasion) {
        this.occasion = occasion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T_occasion{" +
                "occasion=" + occasion +
                ", name='" + name + '\'' +
                '}';
    }
}
