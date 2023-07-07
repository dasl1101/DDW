package com.web.DDW.service;

import com.web.DDW.domain.item.Item;
import com.web.DDW.domain.item.ItemRepository;

import com.web.DDW.web.dto.ItemDto;
import com.web.DDW.web.dto.PostsListResponseDto;
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

    //글작성
    @Transactional
    public Long save(ItemDto.Request dto){
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

}
