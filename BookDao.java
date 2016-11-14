package com.books.dao;

import java.util.List;

import com.books.model.Book;

public interface BookDao {
List<Book> getAllBooks();
Book getBookByIsbn(int isbn);
void deleteBook(int isbn);
void addBook(Book book);
void editBook(Book book);
}
