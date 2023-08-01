package com.web.DDW.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "SELECT image_path FROM Image where image_name = :image_name ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findByImagePath(@Param("image_name") String thumbnail);
}
