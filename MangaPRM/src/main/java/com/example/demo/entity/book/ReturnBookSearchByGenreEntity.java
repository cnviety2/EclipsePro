package com.example.demo.entity.book;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@SqlResultSetMapping(name = "ReturnBookEntityMapping", classes = @ConstructorResult(targetClass = ReturnBookEntity.class, columns = {
		@ColumnResult(name = "bookID", type = String.class), @ColumnResult(name = "title", type = String.class),
		@ColumnResult(name = "thumnail_path", type = String.class) }))
@Entity
@Table(name = "`Book`")
public class ReturnBookSearchByGenreEntity {
	@Id
	private String id;
	private String title;
	private String thumnailPath;

}
