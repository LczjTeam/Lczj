package jx.lczj.viewmodel;

/**
 * Created by 14260 on 2018/7/11.
 */
public class Result {
    private int code;
    private String detail;

    public Result() {
    }

    public Result(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
