package com.web.DDW.web;

import com.web.DDW.service.PostsService;
import com.web.DDW.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
@Controller
//@RequestMapping("/board") // 공통적인 url 설정
public class BoardController {

    private final PostsService postsService;

    @GetMapping("/board/list")
    public String list(Model model, HttpServletRequest request){
        model.addAttribute("posts", postsService.findAllDesc());
        //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장함
        // postsService.findAllDesc() 로 가져온 결과를 posts로 board.html에 전달
        return "board/list";
    }

    @GetMapping("/board/posts-write")
    public String postSave(){
        return "board/posts-write";
    }

    @GetMapping("/board/posts-view/{id}")
    public String view(@PathVariable Long id, Model model) {
        //if문 넣어서 유저이름=owner이면 posts-update페이지로 넘어가게 할 예정
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "board/posts-view";
    }


}
