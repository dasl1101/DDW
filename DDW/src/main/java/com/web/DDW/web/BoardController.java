package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.service.PostsService;
import com.web.DDW.web.dto.PostsResponseDto;
import com.web.DDW.web.dto.PostsUpdateRequestDto;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
@Controller
//@RequestMapping("/board") // 공통적인 url 설정
public class BoardController {

    private final PostsService postsService;


    @GetMapping("/")
    public String index(Model model, @LoginUser UserDto.Response user ){

        if (user != null) {
            model.addAttribute("user", user);
        }
        return "index";
    }

    @GetMapping("/board/list")
    public String list(Model model, HttpServletRequest request, @LoginUser UserDto.Response user ){
        model.addAttribute("posts", postsService.findAllDesc());
        //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장함
        // postsService.findAllDesc() 로 가져온 결과를 posts로 board.html에 전달
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "board/list";
    }

    @GetMapping("/board/posts-write")
    public String postSave(Model model,@LoginUser UserDto.Response user){
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "board/posts-write";
    }

    @GetMapping("/board/posts-view/{id}")
    public String view(@PathVariable Long id, Model model, @LoginUser UserDto.Response user) {
        //if문 넣어서 유저이름=owner이면 posts-update페이지로 넘어가게 할 예정
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        if (user != null) {
            model.addAttribute("user",  user);

            //게시글 작성자 본인인지 확인
            if (dto.getUserId().equals(user.getId())) {
            model.addAttribute("owner", true);
            }
        }
        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);

            return "board/posts-view";
    }


}
