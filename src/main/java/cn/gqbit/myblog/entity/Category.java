package cn.gqbit.myblog.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;


    //分类名称，英文
    @Column(unique = true,nullable = false)
    private String name;

    //别名
    @Column(nullable = false)
    private String alias;

    //和文章表的映射关系，一个分类对应多篇文章，对应关系由多的一方维护
    @OneToMany(mappedBy = "category")
    Set<Article> articles = new HashSet<>();

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }


}
