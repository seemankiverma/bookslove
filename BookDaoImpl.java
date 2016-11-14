package com.books.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.books.model.Book;
@Repository
public class BookDaoImpl implements BookDao{
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}
	public List<Book> getAllBooks() {
		//it will open a new session always.
		Session session=sessionFactory.openSession();
		//Selecting all records from the table
		@SuppressWarnings("unchecked")
		List<Book> books= session.createQuery("from Book").list();
		session.close();// close the session.
		
		return books;
		
	}
	public Book getBookByIsbn(int i) {
		//reading the record from the table
	  Session session=sessionFactory.openSession();
	//select * from book where isbn=i
	//if we call get method,Record doesnot exist it will return null
	//if we call load, if the record doesnt exist it will throw exception
	  Book book=(Book)session.get(Book.class, i);  
	session.close();
	return book;
	}
	
	public void deleteBook(int isbn) {
		Session session=sessionFactory.openSession();
		//select * from book where isbn=?
		Book book=(Book)session.get(Book.class, isbn);
		//delete from book where isbn=?
		session.delete(book);
		//commit the changes
		//changes to the database will become permanent
		session.flush();
		//closing the connection with the database.
		session.close();//close the session
	}

	public void addBook(Book book) {
		Session session=sessionFactory.openSession();
		session.save(book);
		session.close();
	}
	
	public void editBook(Book book) {
		Session session=sessionFactory.openSession();
		//update bookapp set ....where isbn=?
		session.update(book);
		session.flush();
		session.close();
}

}
