package com.web.DDW.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //게시글 댓글 목록 가져옴
    List<Comment> getCommentByPostsOrderById(Posts posts);
    Optional<Comment> findByPostsIdAndId(Long postsId, Long id);

}
