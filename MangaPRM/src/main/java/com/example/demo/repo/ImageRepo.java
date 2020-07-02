package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.chapter.image.ImageEntity;

public interface ImageRepo extends JpaRepository<ImageEntity, String> {

	@Query("SELECT i FROM ImageEntity i WHERE i.chapterID = :chapterID")
	List<ImageEntity> getImagesByChapterID(@Param("chapterID") String chapterID);
}
