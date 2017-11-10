package cn.gqbit.myblog.service;

import cn.gqbit.myblog.entity.Article;
import org.springframework.data.domain.Page;

import java.util.LinkedList;

public interface IArticleService extends IBaseService{


    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    Page<Article> findArticleNoCriteria(Integer page, Integer size);

    /**
     *  带条件的分页查询
     *
     * @param page
     * @param size
     * @return
     */
    Page<Article> findArticleWithCriteria(Integer cid,Integer page, Integer size);

    /**
     * 添加文章
     * @param article
     */
    void addArticle(Article article);

    /**
     * 根据文章id查找
     * @param aid
     * @return
     */
    Article loadArticleByAId(int aid);

    /**
     * 获取上一篇文章
     * @param aid
     * @return
     */
    Article loadPreArticle(int aid);

    /**
     * 获取词下一篇文章
     * @param aid
     * @return
     */
    Article loadNextArticle(int aid);


    void updateArticle(Article article);

    void readCountAdd(int aid);
}
