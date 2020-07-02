package com.example.demo.service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.book.BookEntity;
import com.example.demo.entity.book.ReturnBookEntity;
import com.example.demo.entity.book.ReturnBookSearchByGenreEntity;
import com.example.demo.entity.chapter.ChapterEntity;
import com.example.demo.entity.chapter.image.ImageEntity;
import com.example.demo.entity.genre.GenreEntity;
import com.example.demo.repo.BookRepo;
import com.example.demo.repo.ChapterRepo;
import com.example.demo.repo.GenreRepo;
import com.example.demo.repo.ImageRepo;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static Logger log = LogManager.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private ChapterRepo chapterRepo;

	@Autowired
	private ImageRepo imageRepo;

	@Autowired
	private GenreRepo genreRepo;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<String> getAllTitles() {
		return bookRepo.getAllTitle();
	}

	@Override
	public List<ReturnBookEntity> getMangasByTitle(String title) {
		try {
			List<BookEntity> temp = bookRepo.getBookByTitle(title);
			List<ReturnBookEntity> returnData = new ArrayList<ReturnBookEntity>(temp.size());
			temp.stream().forEach(x -> {
				returnData.add(new ReturnBookEntity(x.getId(), x.getTitle(), x.getThumnailPath()));
			});
			return returnData;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<ReturnBookEntity> getHottestMangas() {
		List<BookEntity> temp = null;
		try {
			temp = bookRepo.findTop10ByOrderByRatingValueDesc();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		List<ReturnBookEntity> returnData = new ArrayStack<ReturnBookEntity>(temp.size());
		temp.stream().forEach(x -> {
			returnData.add(new ReturnBookEntity(x.getId(), x.getTitle(), x.getThumnailPath()));
		});
		return returnData;
	}

	@Override
	public BookEntity getByID(String id) {
		BookEntity returnData = null;
		try {
			if (bookRepo.existsById(id))
				returnData = bookRepo.findById(id).orElse(null);
			return returnData;

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return returnData;
	}

	@Override
	public List<ImageEntity> getImagesByChapterID(String chapterID) {
		List<ImageEntity> returnData = null;
		try {
			if (!chapterRepo.existsById(chapterID))
				return null;
			returnData = imageRepo.getImagesByChapterID(chapterID);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return returnData;
	}

	@Override
	public List<ChapterEntity> getChaptersByBookID(String bookID) {
		List<ChapterEntity> returnData = null;
		try {
			if (!bookRepo.existsById(bookID))
				// return new ArrayList<ChapterEntity>();
				return null;
			returnData = chapterRepo.getChaptersByBookID(bookID);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return returnData;

	}

	@Override
	public List<GenreEntity> getAllGenres() {
		try {
			return genreRepo.findAll();

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<GenreEntity>();
	}

	@Override
	public List<ReturnBookSearchByGenreEntity> getBooksByGenre(int genreID, int quantity) {
		try {
			if (!genreRepo.existsById(genreID))
				return null;
			Query query = entityManager.createNativeQuery("SELECT b.bookID AS 'bookID',b.title AS 'title',b.thumnail_path AS 'thumnail_path' FROM Book b JOIN Book_genres g "
					+ "ON b.bookID = g.bookID WHERE genreID = ?1 ORDER BY rating_value DESC","ReturnBookEntityMapping");
			query.setParameter(1, genreID);
			List<ReturnBookSearchByGenreEntity> temp = query.getResultList();
			if(temp.size() < quantity)
				return null;
			return temp.subList(0, quantity);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
