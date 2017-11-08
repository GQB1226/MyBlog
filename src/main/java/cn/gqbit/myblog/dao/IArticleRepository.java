package cn.gqbit.myblog.dao;

import cn.gqbit.myblog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "articleRepository")
public interface IArticleRepository extends JpaRepository<Article,Integer>,JpaSpecificationExecutor{
    Article findArticleByAid(int aid);
    Article findDistinctFirstByAidBeforeOrderByAidDesc(int aid);
    Article findDistinctFirstByAidAfterOrderByAidAsc(int aid);
    Page<Article>  findArticlesByCid(int cid,Pageable pageable);
//    void s
    @Modifying
    @Query("update Article a set a.body= ?1,a.title= ?2, a.cid=?3 where a.aid=?4")
    void update(String body, String title, Integer cid, Integer aid);
}
