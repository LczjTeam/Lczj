package jx.lczj.model;

import java.io.Serializable;

/**
 * Created by 14260 on 2018/6/10.
 */

/**
 * 管理员
 */
public class T_new implements Serializable{
    private String code;  //   编号
    private String title;   //   标题
    private String issue_date;   //   日期
    private String author;//   作者
    private String keyword; //关键词
    private String filename;    //路径
    private int items;   //
    private String top; //是否置顶
    private String publish; //
    private String photo;   //图片

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "T_new{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", issue_date='" + issue_date + '\'' +
                ", author='" + author + '\'' +
                ", keyword='" + keyword + '\'' +
                ", filename='" + filename + '\'' +
                ", items=" + items +
                ", top='" + top + '\'' +
                ", publish='" + publish + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
