package jx.lczj.model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/10.
 */

/**
 * 管理员
 */
public class T_admin implements Serializable{
    private String admin;  //   用户账号
    private String name;   //   用户名
    private String pwd ;   //   密码 默认 '123456',
    private String isvalid;//  是否有效  默认 '1',

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

    @Override
    public String toString() {
        return "T_admin{" +
                "admin='" + admin + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", isvalid='" + isvalid + '\'' +
                '}';
    }
}
