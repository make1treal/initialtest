package com.example.QnA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/QnA")
    @ResponseBody
    public String index(){
        return "QnA 페이지";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}
