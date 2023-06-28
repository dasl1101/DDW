package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.service.CommentService;
import com.web.DDW.web.dto.CommentDto;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/v1/posts/{id}/comments")
    public ResponseEntity commentSave(@PathVariable Long id, @RequestBody CommentDto.Request dto,
                                      @LoginUser UserDto.Response user){
        return ResponseEntity.ok(commentService.commentSave(user.getNickName(),id, dto));

    }
    //댓글수정
    @PutMapping({"/api/v1/posts/{postsId}/comments/{id}"})
    public ResponseEntity<Long> update (@PathVariable Long postsId, @PathVariable  Long id, @RequestBody CommentDto.Request dto){
        commentService.update(postsId, id, dto);
        System.out.println("댓글수정시작");
        return ResponseEntity.ok(id);
    }

    //댓글삭제
    @DeleteMapping("/posts/{postsId}/comments/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long postsId, @PathVariable Long id){
        commentService.delete(postsId, id);
        System.out.println("댓글삭제시작");
        return ResponseEntity.ok(id);
    }


}
