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
import com.example.demo.entity.book.ReturnBookSearchByGenreEntity;
import com.example.demo.entity.genre.GenreEntity;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/genre")
public class GenreController {

	private static Logger log = LogManager.getLogger(GenreController.class);

	@Autowired
	private BookService service;

	@GetMapping
	@CrossOrigin
	public Object getAllGenres() {
		return new ResponseEntity<ReturnEntity>(new ReturnEntity(service.getAllGenres()), HttpStatus.OK);
	}

	@GetMapping("/{genreID}/{listNum}")
	@CrossOrigin
	public Object getBookByGenre(@PathVariable("genreID") Integer genreID, @PathVariable("listNum") Integer listNum) {
		if (listNum <= 0 || genreID <= 0 || genreID > 54)
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("GenreID must >0,<55 and List must > 0"),
					HttpStatus.BAD_REQUEST);
		try {
			List<ReturnBookSearchByGenreEntity> returnData = service.getBooksByGenre(genreID, listNum);
			if(returnData == null)
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("The list is null"),HttpStatus.BAD_REQUEST);
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(returnData), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.BAD_REQUEST);
	}

}
