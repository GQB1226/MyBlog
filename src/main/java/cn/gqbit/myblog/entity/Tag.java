package cn.gqbit.myblog.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private int tid;

    @Column(unique = true, nullable = false)
    private String tname;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tid=" + tid +
                ", tname='" + tname + '\'' +
                ", articles=" + articles +
                '}';
    }
}
