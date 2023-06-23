package com.web.DDW.service;

import com.web.DDW.domain.posts.Posts;
import com.web.DDW.domain.posts.PostsRepository;
import com.web.DDW.web.dto.PostsListResponseDto;
import com.web.DDW.web.dto.PostsResponseDto;
import com.web.DDW.web.dto.PostsSaveRequestDto;
import com.web.DDW.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    //메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }



    //게시글 단건조회
    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new PostsResponseDto(entity);
    }

    //게시글 전체조회
    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //= .map(posts -> new PostsListResponseDto(posts))
                //postRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환
                .collect(Collectors.toList());
                //→ List로 반환
    }

    //게시글 수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." ));
        //orElseThrow : 값이 없을 때 에러문구 표시
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    //게시글 삭제
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다."));
        postsRepository.delete(posts);
        //JpaRepository에서 delete 메소드를 지원하고 있음
        //엔티티를 파라미터로 삭제할 수도 있고 deleteById 메소드를 이용하면 id로 삭제도 가능

    }

}
