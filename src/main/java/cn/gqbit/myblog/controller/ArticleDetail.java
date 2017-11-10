package cn.gqbit.myblog.controller;


import cn.gqbit.myblog.entity.Article;
import cn.gqbit.myblog.service.IArticleService;
import cn.gqbit.myblog.service.ICategoryService;
import cn.gqbit.myblog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/blog")
public class ArticleDetail {

    @Autowired
    IArticleService articleService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    ITagService tagService;

    @RequestMapping(value = "/id/{articleId}", method = RequestMethod.GET)
    public String showDetail(@PathVariable Integer articleId, Model model) {

        int articleCount = articleService.getTotal();
        int categoryCount = categoryService.getTotal();
        int tagCount = tagService.getTotal();

        //增加阅读次数记录
        articleService.readCountAdd(articleId);

        model.addAttribute("articleTotal", articleCount);
        model.addAttribute("categoryTotal", categoryCount);
        model.addAttribute("tagTotal", tagCount);

        Article result = articleService.loadArticleByAId(articleId);
        if (result != null) {
            model.addAttribute("article", result);
            model.addAttribute("category", result.getCategory().getName());
            model.addAttribute("tags", result.getTags());
            model.addAttribute("prevArticle", articleService.loadPreArticle(articleId));
            model.addAttribute("nextArticle", articleService.loadNextArticle(articleId));

            return "detail";
        }
        return "error";
    }
    @RequestMapping(value="/url/{url}",method=RequestMethod.GET)
    public void content(@PathVariable Integer url,HttpServletResponse resp){
        Article a = articleService.loadArticleByAId(url);
//		System.out.println(a.getMdSource());
        resp.setContentType("text/x-markdown;charset=utf-8");
        try {
            resp.getWriter().write(a.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
