package jx.lczj.viewmodel;

import jx.lczj.model.T_eyeglass;
import jx.lczj.model.T_wearglass;

/**
 * Created by 14260 on 2018/7/11.
 */
public class WearglassVo {

    private T_wearglass t_wearglass;
    private EyeglassVo eyeglassVo;

    @Override
    public String toString() {
        return "WearglassVo{" +
                "t_wearglass=" + t_wearglass +
                ", eyeglassVo=" + eyeglassVo +
                '}';
    }

    public T_wearglass getT_wearglass() {
        return t_wearglass;
    }

    public void setT_wearglass(T_wearglass t_wearglass) {
        this.t_wearglass = t_wearglass;
    }

    public EyeglassVo getEyeglassVo() {
        return eyeglassVo;
    }

    public void setEyeglassVo(EyeglassVo eyeglassVo) {
        this.eyeglassVo = eyeglassVo;
    }
}
