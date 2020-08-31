package com.ebooks.book_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BookPojo {

	private int bookId;
	private String bookName;
	private int bookPrice;
	private String bookImage;
	private String bookAuthor;
	private String bookDescription;

}
