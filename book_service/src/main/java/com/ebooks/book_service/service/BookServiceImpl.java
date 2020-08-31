package com.ebooks.book_service.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebooks.book_service.dao.BookDao;
import com.ebooks.book_service.entity.BookEntity;
import com.ebooks.book_service.pojo.BookPojo;

@Service
public class BookServiceImpl implements BookService {

	static Logger LOG = Logger.getLogger(BookServiceImpl.class.getClass());

	@Autowired
	BookDao bookDao;

	@Override
	public BookPojo getBook(Integer bookId) {
		// TODO Auto-generated method stub
		LOG.info("Enterd getbook()");

		BookPojo bookPojo = null;
		Optional result = bookDao.findById(bookId);
		if (result.isPresent()) {
			BookEntity bookEntity = (BookEntity) result.get();
			bookPojo = new BookPojo(bookEntity.getBookId(), bookEntity.getBookName(), bookEntity.getBookPrice(),
					bookEntity.getBookImage(), bookEntity.getBookAuthor(), bookEntity.getBookDescription());

		}
		LOG.info("Exited getBook()");

		return bookPojo;
	}

	@Override
	public List<BookPojo> getAllBooks() {
		// TODO Auto-generated method stub

		LOG.info("Enterd getAllBooks()");
		List<BookPojo> allBookPojo = new ArrayList();
		Iterable<BookEntity> allBookEntity = bookDao.findAll();
		Iterator itr = allBookEntity.iterator();

		while (itr.hasNext()) {
			BookEntity bookEntity = (BookEntity) itr.next();
			BookPojo bookPojo = new BookPojo(bookEntity.getBookId(), bookEntity.getBookName(),
					bookEntity.getBookPrice(), bookEntity.getBookImage(), bookEntity.getBookAuthor(),
					bookEntity.getBookDescription());
			allBookPojo.add(bookPojo);

		}
		LOG.info("Exited getAllBooks()");
		return allBookPojo;
	}

	@Override
	public BookPojo updateBook(BookPojo bookPojo) {
		// TODO Auto-generated method stub
		LOG.info("Enterd getUpdateBook()");

		Optional<BookEntity> bookEntity = bookDao.findById(bookPojo.getBookId());
		if (bookEntity.isPresent()) {
			BookEntity book = (BookEntity) bookEntity.get();
			book.setBookId(bookPojo.getBookId());
			book.setBookName(bookPojo.getBookName());
			book.setBookPrice(bookPojo.getBookPrice());
			book.setBookImage(bookPojo.getBookImage());
			book.setBookAuthor(bookPojo.getBookAuthor());
			book.setBookDescription(bookPojo.getBookDescription());
			bookDao.save(book);
			return bookPojo;
		}
		LOG.info("Exited updateBook()");
		return null;
	}

	@Override
	public BookPojo addBook(BookPojo bookPojo) {
		// TODO Auto-generated method stub
		LOG.info("Enterd addBook()");
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookName(bookPojo.getBookName());
		bookEntity.setBookPrice(bookPojo.getBookPrice());
		bookEntity.setBookImage(bookPojo.getBookImage());
		bookEntity.setBookAuthor(bookPojo.getBookAuthor());
		bookEntity.setBookDescription(bookPojo.getBookDescription());

		bookEntity = bookDao.saveAndFlush(bookEntity);
		bookPojo.setBookId(bookEntity.getBookId());
		LOG.info("Exited addBook()");
		return bookPojo;
	}

	@Override
	public boolean deleteBook(Integer bookId) {
		// TODO Auto-generated method stub
		LOG.info("Enterd deleteBook()");
		this.bookDao.deleteById(bookId);
		LOG.info("Exited deleteBook()");
		return true;
	}

}
