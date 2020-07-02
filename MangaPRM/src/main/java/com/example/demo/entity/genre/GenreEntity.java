package com.example.demo.entity.genre;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.example.demo.entity.book.BookEntity;
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
@Table(name = "`Genres`")
@JsonIgnoreProperties(value = "books")
public class GenreEntity {
	@Id
	@Column(name = "genreID")
	private int id;

	@Column(name = "genre")
	@Nationalized
	private String genre;

	@ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
	private Set<BookEntity> books;

}
