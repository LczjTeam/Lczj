package jx.lczj.viewmodel;

import jx.lczj.model.T_face;
import jx.lczj.model.T_model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/24.
 */
public class ModelVo  implements Serializable{

    private T_model t_model;
    private T_face t_face;
    private String info;

    public T_model getT_model() {
        return t_model;
    }

    public void setT_model(T_model t_model) {
        this.t_model = t_model;
    }

    public T_face getT_face() {
        return t_face;
    }

    public void setT_face(T_face t_face) {
        this.t_face = t_face;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ModelVo{" +
                "t_model=" + t_model +
                ", t_face=" + t_face +
                ", info='" + info + '\'' +
                '}';
    }
}
