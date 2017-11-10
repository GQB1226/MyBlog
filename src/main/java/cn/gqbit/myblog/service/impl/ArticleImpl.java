package cn.gqbit.myblog.service.impl;

import cn.gqbit.myblog.dao.IArticleRepository;
import cn.gqbit.myblog.entity.Article;
import cn.gqbit.myblog.service.IArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service(value = "articleService")
@Transactional
public class ArticleImpl implements IArticleService {
    @Resource
    IArticleRepository articleRepository;

    @Override
    public Page<Article> findArticleNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        return articleRepository.findAll(pageable);
    }

    @Override
    public Page<Article> findArticleWithCriteria(Integer cid,Integer page, Integer size) {
        Pageable pageable=new PageRequest(page,size, Sort.Direction.ASC,"id");
        return articleRepository.findArticlesByCid(cid,pageable);
    }

    @Override
    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Article loadArticleByAId(int aid) {
        return articleRepository.findArticleByAid(aid);
    }

    @Override
    public Article loadPreArticle(int aid) {
        return articleRepository.findDistinctFirstByAidAfterOrderByAidAsc(aid);
    }

    @Override
    public Article loadNextArticle(int aid) {
        return articleRepository.findDistinctFirstByAidBeforeOrderByAidDesc(aid);
    }

    @Override
    public void updateArticle(Article article) {
        if(article!=null){
//            articleRepository.update(article.getBody(),article.getTitle(),article.getCid(),article.getAid());
            Article tmp=articleRepository.findArticleByAid(article.getAid());
            tmp.setTags(article.getTags());
            tmp.setBody(article.getBody());
            tmp.setTitle(article.getTitle());
            tmp.setCategory(article.getCategory());
            tmp.setCid(article.getCid());
            articleRepository.save(tmp);
        }
    }

    @Override
    public void readCountAdd(int aid) {
        Article article=articleRepository.findArticleByAid(aid);
        article.setReadCount(article.getReadCount()+1);
        articleRepository.save(article);
    }


    @Override
    public int getTotal() {
        Long count=articleRepository.count();
        return count.intValue();
    }
}
