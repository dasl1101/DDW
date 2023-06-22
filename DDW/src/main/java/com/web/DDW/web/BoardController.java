package com.web.DDW.web;

import com.web.DDW.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/board") // 공통적인 url 설정
public class BoardController {


    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request){
        return "board/list";
    }
}
