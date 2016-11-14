package com.books.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.Table;

@Entity
@Table(name="b_bookcategories")
public class Category implements Serializable{ // Category has list of Books
@Id
private int cid;
@Column(name="category")
private String categoryName;
@JsonIgnore
@OneToMany(mappedBy="category")
List<Book> books;
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public List<Book> getBooks() {
	return books;
}
public void setBooks(List<Book> books) {
	this.books = books;
}
}
