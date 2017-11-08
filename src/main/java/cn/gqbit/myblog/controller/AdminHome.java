package cn.gqbit.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminHome {

    @RequestMapping(method = RequestMethod.GET)
    public String adminHome() {
        return "admin/index";
    }

}
