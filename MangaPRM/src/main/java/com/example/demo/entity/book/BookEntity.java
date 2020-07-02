package com.example.demo.entity.book;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;
import org.springframework.lang.Nullable;

import com.example.demo.entity.genre.GenreEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`Book`")
@JsonIgnoreProperties(value = {"genres"})
public class BookEntity {
	@Id
	@Column(name = "bookID")
	@Nationalized
	private String id;

	@Column(name = "title")
	@Nationalized
	private String title;

	@Column(name = "thumnail_path")
	private String thumnailPath;

	@Column(name = "rating_value")
	private float ratingValue;

	@Column(name = "rating_count")
	private float ratingCount;

	@Column(name = "author")
	private String author;

	@Column(name = "detail_content")
	private String detailContent;

	@Column(name = "isLogin")
	@Nullable
	private Boolean isLogin;

	/*
	 * @OneToMany(mappedBy = "bookID", fetch = FetchType.LAZY) private
	 * List<ChapterEntity> chapters;
	 */

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "`Book_genres`", joinColumns = @JoinColumn(name = "bookID"), inverseJoinColumns = @JoinColumn(name = "genreID"))
	private Set<GenreEntity> genres;

}
