package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.book.BookEntity;

@Repository
public interface BookRepo extends JpaRepository<BookEntity, String> {
	
	@Query(value = "SELECT b.title FROM BookEntity b")
	List<String> getAllTitle();
	
	@Query(value = "SELECT b FROM BookEntity b WHERE b.title like %:title%")
	List<BookEntity> getBookByTitle(@Param("title") String title);
	
	//SELECT s FROM Students s ORDER BY s.id DESC LIMIT 1
	@Query(value = "SELECT TOP 10 * FROM Book ORDER BY rating_value DESC",nativeQuery = true)
	public List<BookEntity> findTop10ByOrderByRatingValueDesc();

}
