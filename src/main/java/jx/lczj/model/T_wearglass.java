package jx.lczj.model;

/**
 * Created by 14260 on 2018/7/9.
 */
public class T_wearglass {

    private String mywear;
    private String eyeglass;
    private int degress;
    private int asdegress;
    private int axis;
    private String sign;

    @Override
    public String toString() {
        return "T_wearglass{" +
                "mywear='" + mywear + '\'' +
                ", eyeglass='" + eyeglass + '\'' +
                ", degress=" + degress +
                ", asdegress=" + asdegress +
                ", axis=" + axis +
                ", sign='" + sign + '\'' +
                '}';
    }

    public String getMywear() {
        return mywear;
    }

    public void setMywear(String mywear) {
        this.mywear = mywear;
    }

    public String getEyeglass() {
        return eyeglass;
    }

    public void setEyeglass(String eyeglass) {
        this.eyeglass = eyeglass;
    }

    public int getDegress() {
        return degress;
    }

    public void setDegress(int degress) {
        this.degress = degress;
    }

    public int getAsdegress() {
        return asdegress;
    }

    public void setAsdegress(int asdegress) {
        this.asdegress = asdegress;
    }

    public int getAxis() {
        return axis;
    }

    public void setAxis(int axis) {
        this.axis = axis;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
