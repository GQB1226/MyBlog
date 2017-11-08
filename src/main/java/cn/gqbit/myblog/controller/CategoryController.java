package cn.gqbit.myblog.controller;

import cn.gqbit.myblog.entity.Article;
import cn.gqbit.myblog.entity.Category;
import cn.gqbit.myblog.service.IArticleService;
import cn.gqbit.myblog.service.ICategoryService;
import cn.gqbit.myblog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    IArticleService articleService;

    @Autowired
    ICategoryService categoryService;
    @Autowired
    ITagService tagService;

    @RequestMapping(value = "/{categoryName}",method = RequestMethod.GET)
    public String category(Model model, @PathVariable String categoryName){
        Category category=categoryService.loadCategoryByCname(categoryName);
        Page<Article> articles =articleService.findArticleWithCriteria(category.getCid(),0,10);
        int articleCount = articleService.getTotal();
        int categoryCount = categoryService.getTotal();
        int tagCount = tagService.getTotal();
        model.addAttribute("articleTotal", articleCount);
        model.addAttribute("categoryTotal", categoryCount);
        model.addAttribute("tagTotal", tagCount);
        model.addAttribute("datas",articles);
        model.addAttribute("index", true);
        return "index";
    }
}
