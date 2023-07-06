package com.web.DDW.web.dto;

import com.web.DDW.domain.item.Item;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ItemDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{

        private Long id;

        private String name;


        private int price;

        @NotBlank(message = "제목을 입력해 주세요.")
        private String title;

        private String thumbnail;

        @NotBlank(message = "내용을 입력해 주세요.")
        private String content;

        private String video;

        public Item toEntity(){
            Item item = Item.builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .title(title)
                    .thumbnail(thumbnail)
                    .content(content)
                    .video(video)
                    .build();
            return item;
        }
    }
    @Getter
    public static class Response implements Serializable {
        private final Long id;

        private final String name;

        private final int price;

        private final String title;

        private final String thumbnail;

        private final String content;

        private final String video;

        private final String modifiedDate;


        public Response(Item item){
            this.id = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
            this.title = item.getTitle();
            this.thumbnail = item.getThumbnail();
            this.content = item.getContent();
            this.video = item.getVideo();
            this.modifiedDate = item.getModifiedDate();
        }
    }

}
