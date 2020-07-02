package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.entity.chapter.ChapterEntity;
import com.example.demo.entity.chapter.ReturnChapterEntity;
import com.example.demo.entity.chapter.image.ImageEntity;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

	private static Logger log = LogManager.getLogger(ChapterController.class);

	@Autowired
	private BookService service;

	@GetMapping("/image/{chapterID}")
	@CrossOrigin
	/**
	 * Lấy các images của chapter đó,dựa vào chapterID
	 */
	public Object getChapterImagesByChapterID(@PathVariable("chapterID") String chapterID) {
		String chapterID2 = chapterID.trim();
		if (chapterID2 == null || chapterID2.isEmpty())
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Chapter id can't be empty"),
					HttpStatus.NOT_ACCEPTABLE);
		try {
			List<ImageEntity> returnData = service.getImagesByChapterID(chapterID2);
			if (returnData == null)
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("The chapter doesn't exist"),
						HttpStatus.NOT_FOUND);
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(returnData), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.NOT_FOUND);

	}

	@GetMapping("/{bookID}")
	@CrossOrigin
	/**
	 * Trả về các chapter của manga đó theo mangaID
	 */
	public Object getChaptersByBookID(@PathVariable("bookID") String bookID) {
		String bookID2 = bookID.trim();
		if (bookID2 == null || bookID2.isEmpty())
			return new ResponseEntity<ReturnEntity>(new ReturnEntity("Manga id can't be empty"),
					HttpStatus.NOT_ACCEPTABLE);
		try {
			List<ChapterEntity> tempData = service.getChaptersByBookID(bookID2);
			if(tempData == null || tempData.isEmpty())
				return new ResponseEntity<ReturnEntity>(new ReturnEntity("Can't find the manga"), HttpStatus.NOT_FOUND);
			List<ReturnChapterEntity> returnData = new ArrayList<ReturnChapterEntity>(tempData.size());
			tempData.stream().forEach(x -> {
				returnData.add(new ReturnChapterEntity(x.getId(), x.getChapterName(), x.getDate()));
			});
			return new ResponseEntity<ReturnEntity>(new ReturnEntity(returnData), HttpStatus.OK);

		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return new ResponseEntity<ReturnEntity>(new ReturnEntity("Exception occured"), HttpStatus.NOT_FOUND);
	}

}
