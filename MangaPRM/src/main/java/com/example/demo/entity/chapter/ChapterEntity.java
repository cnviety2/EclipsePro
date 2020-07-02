package com.example.demo.entity.chapter;

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
@Table(name = "`Chapter`")
//@JsonIgnoreProperties(value = {"bookID","images"})
public class ChapterEntity {
	@Id
	@Column(name = "chapterID")
	private String id;

	@Column(name = "chapter_name")
	private String chapterName;

	@Column(name = "upload_date")
	private String date;
	
	@Column(name = "bookID")
	private String bookID;
	
	/*
	 * @OneToMany(mappedBy = "chapterID",fetch = FetchType.EAGER) private
	 * List<ImageEntity> images;
	 */
}
