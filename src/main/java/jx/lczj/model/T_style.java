package jx.lczj.model;


/**
 * 设计管理
 * */
public class T_style {
    private int style;      //设计样式编号
    private String name;    //样式标识

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //只有在封装之后才可以在控制台打印输出，进行调试
    @Override
    public String toString() {
        return "T_style{" +
                "style=" + style +
                ", name='" + name + '\'' +
                '}';
    }

}
