package com.web.DDW.service;

import com.web.DDW.domain.posts.Comment;
import com.web.DDW.domain.posts.CommentRepository;
import com.web.DDW.domain.posts.Posts;
import com.web.DDW.domain.posts.PostsRepository;
import com.web.DDW.domain.user.User;
import com.web.DDW.domain.user.UserRepository;
import com.web.DDW.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long commentSave(Long id, String nickName, CommentDto.Request dto){
        User user = userRepository.findByNickName(nickName);
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException("해당 게시글이 존재하지 않습니다." + id));

        dto.setUser(user);
        dto.setPosts(posts);

        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return dto.getId();

    }
}
