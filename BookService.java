package com.books.services;

import java.util.List;
import com.books.model.Book;
public interface BookService {
List<Book> getAllBooks();
Book getBookByIsbn(int isbn);
void deleteBook(int isbn);
void addBook(Book book);
void editBook(Book book);
}
