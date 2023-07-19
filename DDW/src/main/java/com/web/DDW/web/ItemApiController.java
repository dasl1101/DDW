package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.service.ItemService;
import com.web.DDW.web.dto.ItemDto;
import com.web.DDW.web.dto.PostsSaveRequestDto;
import com.web.DDW.web.dto.PostsUpdateRequestDto;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private final ItemService itemService;

    //글작성
    @PostMapping("/api/v1/item")
    public Long save(@RequestBody ItemDto.Request dto, @LoginUser UserDto.Response user) {
        return itemService.save(dto, user.getNickName());
    }




    //글수정
    @PutMapping("/api/v1/item/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody ItemDto.Request dto) {
        itemService.update(id, dto);
        return ResponseEntity.ok(id);
    }


}
