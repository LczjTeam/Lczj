package jx.lczj.model;

/**
 * Created by WuLinZhi on 2018-07-09.
 */
public class T_wearglass {
    private String mywear; //我的试戴
    private String eyeglass;//镜片
    private int degress;//度数
    private int asdegress; //散光度数
    private float axis;   //散光轴位
    private String sign; //左右标志
    private int wearglass ; //近视远视 '0-近视,1-远视'


    @Override
    public String toString() {
        return "T_wearglass{" +
                "mywear='" + mywear + '\'' +
                ", eyeglass='" + eyeglass + '\'' +
                ", degress=" + degress +
                ", asdegress=" + asdegress +
                ", axis=" + axis +
                ", sign='" + sign + '\'' +
                ", wearglass=" + wearglass +
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

    public float getAxis() {
        return axis;
    }

    public void setAxis(float axis) {
        this.axis = axis;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getWearglass() {
        return wearglass;
    }

    public void setWearglass(int wearglass) {
        this.wearglass = wearglass;
    }
}
