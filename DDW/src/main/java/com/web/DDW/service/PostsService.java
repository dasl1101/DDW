package com.web.DDW.service;

import com.web.DDW.domain.posts.Posts;
import com.web.DDW.domain.posts.PostsRepository;
import com.web.DDW.web.dto.PostsResponseDto;
import com.web.DDW.web.dto.PostsSaveRequestDto;
import com.web.DDW.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    //메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        //orElseThrow : 값이 없을 때 에러문구 표시
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }


}
