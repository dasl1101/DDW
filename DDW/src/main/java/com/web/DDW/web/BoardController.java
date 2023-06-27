package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.service.PostsService;
import com.web.DDW.web.dto.Paginator;
import com.web.DDW.web.dto.PostsResponseDto;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/board") // 공통적인 url 설정
public class BoardController {

    private final PostsService postsService;
    private static final Integer POSTS_PER_PAGE = 10;
    private static final Integer PAGES_PER_BLOCK = 5;

    //대문
    @GetMapping("/")
    public String index(Model model, @LoginUser UserDto.Response user ){

        if (user != null) {
            model.addAttribute("user", user);
        }
        return "index";
    }

    //게시글리스트
    @GetMapping("/board/list")
    public String list(Model model, HttpServletRequest request, @LoginUser UserDto.Response user,
                       @RequestParam(value = "page", defaultValue = "1") Integer page ){
        model.addAttribute("posts", postsService.findAllDesc());
        //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장함
        // postsService.findAllDesc() 로 가져온 결과를 posts로 board.html에 전달
        if (user != null) {
            model.addAttribute("user", user);
        }
        // 페이지네이션
        try {
            Paginator paginator = new Paginator(PAGES_PER_BLOCK, POSTS_PER_PAGE, postsService.count());
            Map<String, Object> pageInfo = paginator.getFixedBlock(page);
            model.addAttribute("pageInfo", pageInfo);
        } catch(IllegalStateException e) {
            model.addAttribute("pageInfo", null);
            System.err.println(e);
        }
        model.addAttribute("posts", postsService.findAllByOrderByIdDesc(page, POSTS_PER_PAGE));
        return "board/list";
    }

    //게시글작성
    @GetMapping("/board/posts-write")
    public String postSave(Model model,@LoginUser UserDto.Response user){
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "board/posts-write";
    }

    //게시글상세
    @GetMapping("/board/posts-view/{id}")
    public String view(@PathVariable Long id, Model model, @LoginUser UserDto.Response user) {
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

    //게시글수정
    @GetMapping("/board/posts-update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser UserDto.Response user) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "board/posts-update";
    }
}
