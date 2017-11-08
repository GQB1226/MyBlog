package cn.gqbit.myblog.controller;


import cn.gqbit.myblog.entity.Article;
import cn.gqbit.myblog.service.IArticleService;
import cn.gqbit.myblog.service.ICategoryService;
import cn.gqbit.myblog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/")
public class Home {

    @Autowired
    IArticleService articleService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    ITagService tagService;

    /**
     * @param model
     * @param page  页数
     * @param size  每页展示的个数
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        int articleCount = articleService.getTotal();
        int categoryCount = categoryService.getTotal();
        int tagCount = tagService.getTotal();

        Page<Article> articles = articleService.findArticleNoCriteria(0, 10);
        model.addAttribute("articleTotal", articleCount);
        model.addAttribute("categoryTotal", categoryCount);
        model.addAttribute("tagTotal", tagCount);
        model.addAttribute("datas", articles);
        model.addAttribute("index", true);

        return "index";
    }

    /**
     * 跳转登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

}
