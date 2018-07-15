package jx.lczj.model;

import java.io.Serializable;

public class T_services implements Serializable{
    private String weixin;
    private String company;

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "T_services{" +
                "weixin='" + weixin + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
