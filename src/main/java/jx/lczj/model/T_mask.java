package jx.lczj.model;


/**
 * 膜层信息
 */
public class T_mask {

    private int mask;   //名称
    private String name;    //膜层标识


    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T_mask{" +
                "mask=" + mask +
                ", name='" + name + '\'' +
                '}';
    }
}
