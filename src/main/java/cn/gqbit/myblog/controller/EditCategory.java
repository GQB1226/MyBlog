package cn.gqbit.myblog.controller;

import cn.gqbit.myblog.entity.Category;
import cn.gqbit.myblog.service.ICategoryService;
import cn.gqbit.myblog.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/admin/category")
public class EditCategory {

    @Autowired
    ICategoryService categoryService;

    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public String categoryList(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page,
                               @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Page<Category> categories = categoryService.findTagNoCriteria(page, size);
        model.addAttribute("datas", categories);
        return "admin/category/categoryList";
    }

    @RequestMapping(value = "/addJump", method = RequestMethod.GET)
    public String addJump() {
        return "admin/category/categoryAdd";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo save(Category category) {
        if (category != null) {
            if (categoryService.checkExit(category)) {
                return new ResultInfo("error", "分类的名称或别名已存在");
            }
            categoryService.save(category);
            return new ResultInfo("success", "添加分类成功！");
        }
        return new ResultInfo("error", "添加分类失败");
    }

    @RequestMapping(value = "/editJump/{categoryId}", method = RequestMethod.GET)
    public String categoryEdit(Model model, @PathVariable Integer categoryId) {
        Category category = categoryService.loadCategoryByCid(categoryId);
        model.addAttribute("category", category);
        return "admin/category/categoryEdit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo categoryUpdate(Category category) {
        try {
            //解码
            category.setName(URLDecoder.decode(category.getName(), "UTF-8"));
            category.setAlias(URLDecoder.decode(category.getAlias(), "UTF-8"));
            //检查是否存在
            if (categoryService.checkExit(category)) {
                return new ResultInfo("error", "分类的名称或别名已存在");
            } else {
                categoryService.update(category);
                return new ResultInfo("success", "修改成功");
            }
        } catch (UnsupportedEncodingException e) {
            return new ResultInfo("error", "字符串解析错误,请稍后在尝试");
        }

    }
}
