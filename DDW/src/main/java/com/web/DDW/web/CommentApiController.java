package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.service.CommentService;
import com.web.DDW.service.UserService;
import com.web.DDW.web.dto.CommentDto;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentDto.Request dto,
                                      @LoginUser UserDto.Response user){
        return ResponseEntity.ok(commentService.commentSave(id,user.getNickName(), dto));

    }

}