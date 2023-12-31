package com.web.DDW.service;

import com.web.DDW.domain.item.ImageRepository;
import com.web.DDW.domain.item.Item;
import com.web.DDW.domain.item.ItemRepository;

import com.web.DDW.domain.posts.Posts;
import com.web.DDW.domain.user.User;
import com.web.DDW.domain.user.UserRepository;
import com.web.DDW.web.dto.ItemDto;
import com.web.DDW.web.dto.PostsListResponseDto;
import com.web.DDW.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    //글작성
    @Transactional
    public Long save(ItemDto.Request dto, String nickName){

        String thumbnail_ = imageRepository.findByImagePath(dto.getThumbnail());
        thumbnail_ = thumbnail_.replaceAll("C:/ddwProjectGit/DDW/src/main/resources/static/img/","");
        dto.setThumbnail(thumbnail_);
        User user = userRepository.findByNickName(nickName);
        dto.setUser(user);
        Item item = dto.toEntity();
        itemRepository.save(item);

        return item.getId();
    }

    //전체상품리스트 조회
    @Transactional(readOnly = true)
    public List<ItemDto.Response> findAllDesc(){
        return itemRepository.findAllDesc().stream()
                .map(ItemDto.Response::new)
                .collect(Collectors.toList());
        //→ List로 반환
    }


    //페이지네이션
    @Transactional(readOnly = true)
    public List<ItemDto.Response> findAllByOrderByIdDesc(Integer pageNum, Integer postsPerPage) {
        Page<Item> page = itemRepository.findAll(
                // PageRequest의 page는 0부터 시작
                PageRequest.of(pageNum - 1, postsPerPage,
                        Sort.by(Sort.Direction.DESC, "id")
                ));
        return page.stream()
                .map(ItemDto.Response::new)
                .collect(Collectors.toList());
    }

    public Long count() { //게시판 전체 글 수를 반환
        return itemRepository.count();
    }


    //게시글 단건조회
    public ItemDto.Response findById(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()
        ->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new ItemDto.Response(item);
    }

    //게시글 수정
    @Transactional
    public void update(Long id, ItemDto.Request dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id ));
        //orElseThrow : 값이 없을 때 에러문구 표시
        if(dto.getThumbnail() != ""){
            System.out.println("System.out.println(dto.getThumbnail());값있음" + dto.getThumbnail());
            String thumbnail_ = imageRepository.findByImagePath(dto.getThumbnail());

            thumbnail_ = thumbnail_.replaceAll("C:/ddwProjectGit/DDW/src/main/resources/static/img/", "");
            dto.setThumbnail(thumbnail_);

        }else{
            System.out.println("System.out.println(dto.getThumbnail());값없음" + dto.getThumbnail());
            dto.setThumbnail(item.getThumbnail());

        }

        item.update(dto.getTitle(), dto.getTitle(), dto.getPrice(),dto.getContent(),dto.getThumbnail());

    }

    //게시글 삭제
    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        itemRepository.delete(item);
        //JpaRepository에서 delete 메소드를 지원하고 있음
        //엔티티를 파라미터로 삭제할 수도 있고 deleteById 메소드를 이용하면 id로 삭제도 가능

    }

}
