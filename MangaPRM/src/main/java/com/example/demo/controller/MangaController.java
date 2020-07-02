package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ReturnEntity;
import com.example.demo.entity.book.BookEntity;
import com.example.demo.entity.book.ReturnBookEntity;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.BookService;
import com.example.demo.service.implement.BookServiceImpl;

@RestController
@RequestMapping("/manga")
public class MangaController {

	private static Logger log = LogManager.getLogger(MangaController.class);

	@Autowired
	private BookService service;

	@Autowired
	BookRepo repo;// test

	@GetMapping("/test/{title}") // test
	public Object test(@PathVariable("title") String title) {
		return new ResponseEntity<List<BookEntity>>(repo.getBookByTitle(title), HttpStatus.OK);
	}

	@GetMapping("/all") // test
	public Object getAllTitles() {
		return new ResponseEntity<List<String>>(service.getAllTitles(), HttpStatus.OK);
	}

	@GetMapping("/title/{search}")
	@CrossOrigin
	/**
	 * Lấy các truyện có title gần giống search value
	 */
	public Object getBookByTitle(@PathVariable(value = "search") String title) {
		String title2 = title.trim();
		if (title2 == null || title2.isEmpty())
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Search can't be empty"),
					HttpStatus.NOT_ACCEPTABLE);
		try {
			List<ReturnBookEntity> dataReturn = service.getMangasByTitle(title2);
			if (dataReturn == null || dataReturn.isEmpty())
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("Can't find the manga"), HttpStatus.NOT_FOUND);
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(service.getMangasByTitle(title2)), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.NOT_FOUND);
	}

	@GetMapping("/hottestManga")
	@CrossOrigin
	/**
	 * Lấy 10 truyện rate cao nhất
	 */
	public Object getTop10MangasByRateValue() {
		return new ResponseEntity<ReturnEntity>(new ReturnEntity(service.getHottestMangas()), HttpStatus.OK);
	}

	@GetMapping("/{bookID}")
	@CrossOrigin
	/**
	 * Lấy thông tin 1 bộ manga theo id
	 */
	public Object getMangaByMangaID(@PathVariable("bookID") String bookID) {
		String bookID2 = bookID.trim();
		if (bookID2 == null || bookID2.isEmpty())
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Book id can't be empty"),
					HttpStatus.NOT_ACCEPTABLE);
		try {
			BookEntity returnData = service.getByID(bookID2);
			if (returnData == null)
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("The manga doesn't exist"),
						HttpStatus.NOT_FOUND);
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(returnData), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.NOT_FOUND);
	}

}
