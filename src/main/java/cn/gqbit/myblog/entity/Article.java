package cn.gqbit.myblog.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_article")
public class Article {


    private int id;
    //文章编号
    private int aid;
    //文章题目
    private String title;
    //文章内容
    private String body;
    //发表时间
    private String time;
    //作者
    private String author;
    //所属分类编号
    private int cid;
    //阅读总数
    private int readCount;
    //文章状态
    private int status;

    @Column(nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private Category category;

    private Set<Tag> tags = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(unique = true, nullable = false)
    public int getAid() {
        return this.aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(length = 16777216)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    @ManyToOne
    @JoinTable(name = "t_article_category", joinColumns = {@JoinColumn(name = "aid")}, inverseJoinColumns = {
            @JoinColumn(name = "cid")
    })
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name = "t_article_tag", joinColumns = {@JoinColumn(name = "aid")}, inverseJoinColumns = {
            @JoinColumn(name = "tid")
    })
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", aid=" + aid +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", time='" + time + '\'' +
                ", author='" + author + '\'' +
                ", cid=" + cid +
                ", readCount=" + readCount +
                ", status=" + status +
                ", category=" + category +
                ", tags=" + tags +
                '}';
    }


}
