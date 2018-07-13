package jx.lczj.viewmodel;

/**
 * Created by 14260 on 2018/7/10.
 */
public class SrcDstVo {

    private  String  src;
    private  String  dst;

    @Override
    public String toString() {
        return "SrcDstVo{" +
                "src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                '}';
    }

    public SrcDstVo() {
    }

    public SrcDstVo(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
