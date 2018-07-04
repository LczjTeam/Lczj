package jx.lczj.viewmodel;

import jx.lczj.model.T_admin;
import jx.lczj.model.T_news;

/**
 * Created by 14260 on 2018/6/23.
 */
public class NewsVo {

    private T_news t_news;
    private T_admin t_admin;

    @Override
    public String toString() {
        return "NewsVo{" +
                "t_news=" + t_news +
                ", t_admin=" + t_admin +
                '}';
    }

    public T_news getT_news() {
        return t_news;
    }

    public void setT_news(T_news t_news) {
        this.t_news = t_news;
    }

    public T_admin getT_admin() {
        return t_admin;
    }

    public void setT_admin(T_admin t_admin) {
        this.t_admin = t_admin;
    }
}
