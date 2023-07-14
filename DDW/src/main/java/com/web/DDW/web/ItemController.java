package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.domain.item.ItemPath;
import com.web.DDW.service.ItemService;
import com.web.DDW.web.dto.ItemDto;
import com.web.DDW.web.dto.Paginator;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private static final Integer POSTS_PER_PAGE = 10;
    private static final Integer PAGES_PER_BLOCK = 5;

    @GetMapping("/shop/shop-list")
    public String shopList(Model model, @LoginUser UserDto.Response user,
    @RequestParam(value = "page", defaultValue = "1") Integer page){
        //이미지경로
        model.addAttribute("IMGPATH", ItemPath.IMGPATH.getPath());

        if (user != null) {
            model.addAttribute("user", user);
        }

        // 페이지네이션
        try {
            Paginator paginator = new Paginator(PAGES_PER_BLOCK, POSTS_PER_PAGE, itemService.count());
            Map<String, Object> pageInfo = paginator.getFixedBlock(page);
            model.addAttribute("pageInfo", pageInfo);
        } catch(IllegalStateException e) {
            model.addAttribute("pageInfo", null);
            System.err.println(e);
        }

        //썸네일 파일명을 db에 넣기 위한 코드
        model.addAttribute("item", itemService.findAllByOrderByIdDesc(page, POSTS_PER_PAGE));

        return "/shop/shop-list";

    }


    //글쓰기
    @GetMapping("/shop/shop-write")
    public String shopPostSave(Model model,@LoginUser UserDto.Response user){
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "/shop/shop-write";
    }

    //게시글상세
    @GetMapping("/shop/shop-view/{id}")
    public String shopPostView(@PathVariable Long id, Model model, @LoginUser UserDto.Response user){
        ItemDto.Response dto = itemService.findById(id);
        model.addAttribute("item", dto);
        if (user != null) {
            model.addAttribute("user", user);

            //게시글 작성자 본인인지 확인
            if (dto.getUserId().equals(user.getId())) {
                model.addAttribute("owner", true);
            }
        }
        return "/shop/shop-view";
    }



}
