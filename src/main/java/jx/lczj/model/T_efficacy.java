package jx.lczj.model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/12.
 */

/**
 * 功能
 */
public class T_efficacy implements Serializable {

    private int efficacy;   //功能
    private String name;    //名称


    public int getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(int efficacy) {
        this.efficacy = efficacy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T_efficacy{" +
                "efficacy=" + efficacy +
                ", name='" + name + '\'' +
                '}';
    }
}
