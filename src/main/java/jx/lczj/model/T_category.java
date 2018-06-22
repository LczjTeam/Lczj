package jx.lczj.model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/12.
 */

/**
 * 类别
 */
public class T_category implements Serializable {

    private  int  category;  //编号
    private String name;     //名称

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T_category{" +
                "category=" + category +
                ", name='" + name + '\'' +
                '}';
    }
}
