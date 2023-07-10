package com.web.DDW.web.dto;

import com.web.DDW.domain.item.Image;
import com.web.DDW.domain.item.Item;
import lombok.*;

public class ImageDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private String imageName;
        private String imagePath;


        public Image toEntity(){
            Image image = Image.builder()
                    .id(id)
                    .imageName(imageName)
                    .imagePath(imagePath)
                    .build();
            System.out.println("ssssss"+image.getId());
            return image;
        }

    }
    @Getter
    public static class Response{
        private Long id;
        private String imageName;
        private String imagePath;

        public Response(Image image){
            System.out.println("asdasdasdasd"+image);
            this.id = image.getId();
            this.imageName = image.getImageName();
            this.imagePath = image.getImagePath();
        }
    }

}
