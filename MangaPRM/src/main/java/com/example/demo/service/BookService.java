package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.book.BookEntity;
import com.example.demo.entity.book.ReturnBookEntity;
import com.example.demo.entity.book.ReturnBookSearchByGenreEntity;
import com.example.demo.entity.chapter.ChapterEntity;
import com.example.demo.entity.chapter.image.ImageEntity;
import com.example.demo.entity.genre.GenreEntity;

/**
 * Những method nào trả về object dựa vào id truyền vào đều sẽ trả về null nếu
 * ko tìm ra
 */
public interface BookService {

	/**
	 * Trả về list các title của tất cả manga
	 */
	List<String> getAllTitles();

	/**
	 * Trả về list các manga có chứa title
	 */
	List<ReturnBookEntity> getMangasByTitle(String title);

	/**
	 * Top 10 manga có lượt vote cao nhất
	 */
	List<ReturnBookEntity> getHottestMangas();

	/**
	 * Trả về 1 manga dựa vào id,null nếu ko tìm ra
	 */
	BookEntity getByID(String id);

	/**
	 * Trả về 1 chapter,rồi từ entity chapter này lấy list các image của nó
	 */
	List<ImageEntity> getImagesByChapterID(String chapterID);

	/**
	 * Trả về những chapter của manga này
	 */
	List<ChapterEntity> getChaptersByBookID(String bookID);
	
	/**
	 * Trả về tất cả genre trong db
	 * */
	List<GenreEntity> getAllGenres();
	
	/**
	 * Trả về những manga theo genreID
	 * */
	List<ReturnBookSearchByGenreEntity> getBooksByGenre(int genreID,int quantity);
}
