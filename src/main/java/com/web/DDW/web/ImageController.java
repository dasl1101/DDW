package com.web.DDW.web;

import com.web.DDW.domain.item.Image;
import com.web.DDW.domain.item.ItemPath;
import com.web.DDW.service.ImageService;
import com.web.DDW.web.dto.ImageDto;
import com.web.DDW.web.dto.ItemDto;
import com.web.DDW.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final ResourceLoader resourceLoader;

    @PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam(value ="file", required=false) MultipartFile file) {
        try {
             Image image = imageService.store(file);
            System.out.println("image get id = "+ image.getId() + image.getImageName() + image.getImagePath());
             return ResponseEntity.ok().body("/image/" + image.getId());
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> serveFile(@PathVariable Long id){
        try {
            ImageDto.Response dto = imageService.findById(id);
            Resource resource = resourceLoader.getResource("file:" + dto.getImagePath());
            return ResponseEntity.ok().body(resource);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
