package cn.gqbit.myblog.controller;


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

@Controller
@RequestMapping("/admin/tag")
public class EditTag {

    @Autowired
    ITagService tagService;

    @RequestMapping(value = "/tagList",method = RequestMethod.GET)
    public String editTag(Model model, @RequestParam(name = "page",defaultValue = "0")Integer page,
                          @RequestParam(name = "size",defaultValue = "20")Integer size)
    {
        Page<Tag> tags = tagService.findTagNoCriteria(page,size);
        model.addAttribute("datas",tags);
        return "admin/tag/tagList";
    }

    @RequestMapping(value = "/addJump",method = RequestMethod.GET)
    public String addTag(){
        return "admin/tag/tagAdd";
    }

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo saveTag(Tag tag){
        if(tag!=null){
            if(tagService.loadTagByTname(tag.getTname())!=null){
                return new ResultInfo("error","标签已经存在！");
            }
            tag.setTid(tagService.getTotal()+1);
            tagService.save(tag);
            return new ResultInfo("success","添加成功！");
        }
        return new ResultInfo("error","出现错误，稍后重试!");
    }
}
