package com.ebooks.book_service.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebooks.book_service.pojo.BookPojo;
import com.ebooks.book_service.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("ebooks")
public class BookController {

	static Logger LOG = Logger.getLogger(BookController.class.getClass());

	@Autowired
	BookService bookService;

	@GetMapping("/book/{bookId}")
	BookPojo getBook(@PathVariable("bookId") Integer bookId) {
		LOG.info("entered getBook()");
		LOG.info("exited getBook()");
		return bookService.getBook(bookId);
	}

	@GetMapping("/book/all")

	List<BookPojo> getAllBooks() {
		LOG.info("entered the end point\'/book/all'");
		LOG.info("exited the end point\'/book/all'");
		return bookService.getAllBooks();
	}

	@DeleteMapping("/deleteBook/{bookId}")
	public boolean deleteBook(@PathVariable("bookId") int bookId) {
		LOG.info("entered deleteBook()");
		LOG.info("exited deleteBook()");
		return bookService.deleteBook(bookId);
	}

	@PostMapping("/addBook")
	BookPojo addBook(@RequestBody BookPojo bookPojo) {
		LOG.info("entered the end point\'/addBook'");
		LOG.info("exited the end point\'/addBook'");
		return bookService.addBook(bookPojo);

	}

	@PutMapping("updateBook/{bookId}")
	BookPojo updateBook(@RequestBody BookPojo bookPojo) {
		LOG.info("entered the end point\'/updateBook'");
		LOG.info("exited the end point\'/updateBook'");
		return bookService.updateBook(bookPojo);
	}

}
