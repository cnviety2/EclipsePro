package com.example.demo.entity.chapter.image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Chapter_image`")
//@JsonIgnoreProperties(value = "chapterID")
public class ImageEntity {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "url")
	private String url;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "chapter_id", nullable = false) private ChapterEntity
	 * chapterID;
	 */
	
	@Column(name = "chapter_id")
	private String chapterID;
	

}
