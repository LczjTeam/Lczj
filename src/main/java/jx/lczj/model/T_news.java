package jx.lczj.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 14260 on 2018/6/23.
 */
public class T_news implements Serializable {

    private String code ;
    private String title ;
    private String author ;
    private String keyword;
    private int items;
    private String top;
    private String publish ;
    private Date issue_date;
    private String photo;
    private String filename;

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

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "T_news{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", keyword='" + keyword + '\'' +
                ", items=" + items +
                ", top='" + top + '\'' +
                ", publish='" + publish + '\'' +
                ", issue_date=" + issue_date +
                ", photo='" + photo + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
