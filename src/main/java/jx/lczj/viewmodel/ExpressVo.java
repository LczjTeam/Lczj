package jx.lczj.viewmodel;

/**
 * Created by 14260 on 2018/7/20.
 */
public class ExpressVo {
    Object object;
    String express;
    String expressid;

    @Override
    public String toString() {
        return "ExpressVo{" +
                "object=" + object +
                ", express='" + express + '\'' +
                ", expressid='" + expressid + '\'' +
                '}';
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }
}
