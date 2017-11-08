package cn.gqbit.myblog.controller;


import cn.gqbit.myblog.entity.Article;
import cn.gqbit.myblog.entity.Category;
import cn.gqbit.myblog.entity.Tag;
import cn.gqbit.myblog.service.IArticleService;
import cn.gqbit.myblog.service.ICategoryService;
import cn.gqbit.myblog.service.ITagService;
import cn.gqbit.myblog.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin/article")
public class EditArticle {

    @Autowired
    IArticleService articleService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    ITagService tagService;


    private SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 文章管理主页面
     *
     * @return
     */
    @RequestMapping(value = "/articleList", method = RequestMethod.GET)
    public String articleList(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<Article> articles = articleService.findArticleNoCriteria(page, size);
        model.addAttribute("datas", articles);
        return "admin/article/articleList";
    }


    /**
     * 选择分类和标签
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String articleBeforeSubmit(Model model) {
        model.addAttribute("tagList", tagService.listAllTag());
        model.addAttribute("categoryList", categoryService.listAllCategory());
        return "admin/article/beforeSubmit";
    }

    @RequestMapping(value = "/initPage", method = RequestMethod.GET)
    public Page<Article> initArticlePage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                         @RequestParam(name = "start", defaultValue = "0") Integer start,
                                         @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        Page<Article> pages = articleService.findArticleNoCriteria(start, limit);
        return pages;
    }

    /**
     * 保存文章
     *
     * @param article
     * @param tags
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo articleSave(Article article, int[] tags) {
        try {
            // 解码文章内容防止出现部分特殊字符串被转义
            article.setTitle(URLDecoder.decode(article.getTitle(), "UTF-8"));
            article.setBody(URLDecoder.decode(article.getBody(), "UTF-8"));
            article.setCategory(categoryService.loadCategoryByCid(article.getCid()));
            article.setStatus(0);
            //目前文章的id标号是自增
            //TODO 文章的Id设计
            article.setAid(articleService.getTotal() + 1);
            // User user = userService.getCurrentUser();c
            article.setAuthor("GQB1226");
            Date now = new Date();
            article.setTime(dateTime.format(now));
            HashSet<Tag> set = new HashSet<>();
            for (int i = 0; i < tags.length; i++) {
                set.add(tagService.loadTagByTid(tags[i]));
            }
            article.setTags(set);
            articleService.addArticle(article);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResultInfo("error", e.getMessage());
        }
        return new ResultInfo("success", "add article success");
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public String loadArticle(Integer categoryId, String tagIds, String title, Model model) {
        Page<Article> page = articleService.findArticleNoCriteria(0, 10);
        model.addAttribute("articleList", page);
        return "admin/article/articleTable";
    }

    /**
     * 文章添加页面
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "admin/article/articleAdd";
    }

    /**
     * TODO:文章状态修改
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/updateStatue", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo updateStatue(Integer id, Integer status) {
        Article article = articleService.loadArticleByAId(id);
        if (status.equals(article.getStatus())) {
            article.setStatus(1 - status);
            return new ResultInfo("success", "修改状态成功");
        }
        return new ResultInfo("error", "状态不一致");
    }

    /**
     * 文章修改
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/editJump",method = RequestMethod.GET)
    public String editArticle(Model model,Integer id){
        Article article= articleService.loadArticleByAId(id);
        if(article!=null){
            model.addAttribute("article",article);
        }
        return "admin/article/articleEdit";
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.GET)
    public String updateArticleInfo(Model model,@RequestParam(value = "articleId") Integer articleId){
        Article article=articleService.loadArticleByAId(articleId);
        List<Category> categoryList = categoryService.listAllCategory();
        List<Tag> tagList = tagService.listAllTag();
        model.addAttribute("article",article);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        return "admin/article/articleEditInfo";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateArticle(Article article,int[] tags){
        try {
            // 解码文章内容防止出现部分特殊字符串被转义
            article.setTitle(URLDecoder.decode(article.getTitle(), "UTF-8"));
            article.setBody(URLDecoder.decode(article.getBody(), "UTF-8"));
            article.setCategory(categoryService.loadCategoryByCid(article.getCid()));
            HashSet<Tag> set = new HashSet<>();
            for (int i = 0; i < tags.length; i++) {
                set.add(tagService.loadTagByTid(tags[i]));
            }
            article.setTags(set);
            articleService.updateArticle(article);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResultInfo("error", e.getMessage());
        }
        return new ResultInfo("success", "add article success");
    }

}
