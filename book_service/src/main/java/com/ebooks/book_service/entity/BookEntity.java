package com.ebooks.book_service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "book_table")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "book_id")
	private int bookId;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "book_price")
	private int bookPrice;

	@Column(name = "book_image")
	private String bookImage;

	@Column(name = "book_author")
	private String bookAuthor;

	@Column(name = "book_description")
	private String bookDescription;

}
