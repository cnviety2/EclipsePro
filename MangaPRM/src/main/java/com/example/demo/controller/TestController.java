package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.book.BookEntity;
import com.example.demo.entity.genre.GenreEntity;
import com.example.demo.repo.BookRepo;
import com.example.demo.repo.TestRepo;
import com.example.demo.repo.UserRepo;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestRepo repo;
	
	@Autowired
	BookRepo repo2;
	
	@Autowired
	UserRepo userRepo;

	@GetMapping("/hello")
	public Object asd() {
		BookEntity book=repo2.findById("FRVZHY3M8LOBO").orElse(null);
		//System.out.println(book.getChapters().size());
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}
}
