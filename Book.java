package com.books.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="b_bookapp")
public class Book implements Serializable {
	@Id
@Column (name="isbn")
@GeneratedValue (strategy = GenerationType.AUTO)
private int isbn;
@NotEmpty(message="Title is mandatory")
private String title;
//@Column(name="publisher")
@NotEmpty(message="Publication is mendatory")
private String publication;
@Min(value=100,message="Minimum value Shoulb be greter than 100")
private int price;
private String author;
@ManyToOne
@JoinColumn(name="cid")// foregin key column in bookapp table
private Category category;
@Transient
private MultipartFile bookImage;

public MultipartFile getBookImage() {
	return bookImage;
}
public void setBookImage(MultipartFile bookImage) {
	this.bookImage = bookImage;
}
public int getIsbn() {
	return isbn;
}
public void setIsbn(int isbn) {
	this.isbn = isbn;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getPublication() {
	return publication;
}
public void setPublication(String publication) {
	this.publication = publication;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}

}