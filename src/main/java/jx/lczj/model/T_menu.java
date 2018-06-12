package jx.lczj.model;

/**
 * Created by 14260 on 2018/6/10.
 */

/**
 * 菜单
 */
public class T_menu {

    private String menu;    //编号  not null,
    private String title;   //名称 not null,
    private String parents; //父节点 default '0',
    private String url;     //路径 default '',
    private String method;  //方法 default '',
    private int sort_no;    //序号 default 0,
    private int visible;    //是否可见 default 1,
    private String css;     //样式 default '',


    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getSort_no() {
        return sort_no;
    }

    public void setSort_no(int sort_no) {
        this.sort_no = sort_no;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "T_menu{" +
                "menu='" + menu + '\'' +
                ", title='" + title + '\'' +
                ", parents='" + parents + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", sort_no=" + sort_no +
                ", visible=" + visible +
                ", css='" + css + '\'' +
                '}';
    }
}
