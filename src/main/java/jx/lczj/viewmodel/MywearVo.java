package jx.lczj.viewmodel;

import jx.lczj.model.*;

import java.util.List;

/**
 * Created by 14260 on 2018/7/11.
 */
public class MywearVo {

    private T_mywear t_mywear;
    private T_color t_color;
    private T_face t_face;
    private T_occasion t_occasion;
    private GoodsVo goodsVo;
    private WearglassVo leftEyeglass;
    private WearglassVo rightEyeglass;

    @Override
    public String toString() {
        return "MywearVo{" +
                "t_mywear=" + t_mywear +
                ", t_color=" + t_color +
                ", t_face=" + t_face +
                ", t_occasion=" + t_occasion +
                ", goodsVo=" + goodsVo +
                ", leftEyeglass=" + leftEyeglass +
                ", rightEyeglass=" + rightEyeglass +
                '}';
    }

    public T_mywear getT_mywear() {
        return t_mywear;
    }

    public void setT_mywear(T_mywear t_mywear) {
        this.t_mywear = t_mywear;
    }

    public T_color getT_color() {
        return t_color;
    }

    public void setT_color(T_color t_color) {
        this.t_color = t_color;
    }

    public T_face getT_face() {
        return t_face;
    }

    public void setT_face(T_face t_face) {
        this.t_face = t_face;
    }

    public T_occasion getT_occasion() {
        return t_occasion;
    }

    public void setT_occasion(T_occasion t_occasion) {
        this.t_occasion = t_occasion;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }


    public WearglassVo getLeftEyeglass() {
        return leftEyeglass;
    }

    public void setLeftEyeglass(WearglassVo leftEyeglass) {
        this.leftEyeglass = leftEyeglass;
    }

    public WearglassVo getRightEyeglass() {
        return rightEyeglass;
    }

    public void setRightEyeglass(WearglassVo rightEyeglass) {
        this.rightEyeglass = rightEyeglass;
    }
}
