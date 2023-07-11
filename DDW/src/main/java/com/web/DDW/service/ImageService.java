package com.web.DDW.service;

import com.web.DDW.domain.item.Image;
import com.web.DDW.domain.item.ImageRepository;
import com.web.DDW.domain.item.Item;
import com.web.DDW.domain.item.ItemRepository;
import com.web.DDW.domain.posts.Posts;
import com.web.DDW.web.dto.ImageDto;
import com.web.DDW.web.dto.ItemDto;
import com.web.DDW.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    private final Path rootLocation;
    public ImageService(String uploadPath) {
        this.rootLocation = Paths.get(uploadPath);
        System.out.println(rootLocation.toString());
    }

    @Transactional
    public Image store(MultipartFile file) throws Exception {
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }

            String imageName = fileSave(rootLocation.toString(), file);
            ImageDto.Request image = new ImageDto.Request();
            image.setImageName(file.getOriginalFilename());
            image.setImagePath(rootLocation.toString().replace(File.separatorChar, '/') + '/' + imageName);

            Image image_ = image.toEntity();
            imageRepository.save(image_);

            System.out.println("asdasdsadasdas"+image_.getId());
            return image_;

        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

        @Transactional
        public ImageDto.Response findById(Long id){
           Image image = imageRepository.findById(id).orElseThrow(()
                    ->new IllegalArgumentException("해당 이미지가 없습니다. id = " + id));
            return new ImageDto.Response(image);
        }

    public String fileSave(String rootLocation, MultipartFile file) throws IOException {
        File uploadDir = new File(rootLocation);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // saveFileName 생성

        UUID uuid = UUID.randomUUID();
        String saveImageName = uuid.toString() + file.getOriginalFilename();
        File saveFile = new File(rootLocation, saveImageName);
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return saveImageName;
    }

    }

