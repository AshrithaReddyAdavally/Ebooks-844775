package com.ebooks.book_service.service;

import java.util.List;

import com.ebooks.book_service.pojo.BookPojo;

public interface BookService {

	BookPojo getBook(Integer bookId);

	List<BookPojo> getAllBooks();

	BookPojo updateBook(BookPojo bookPojo);

	BookPojo addBook(BookPojo bookPojo);

	boolean deleteBook(Integer bookId);

}
