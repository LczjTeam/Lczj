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
    private String occasion; //使用场合  '只有固定的几个值1-室内，2-室外，3-运动，保存代码';

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

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    @Override
    public String toString() {
        return "T_category{" +
                "occasion='" + occasion + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
