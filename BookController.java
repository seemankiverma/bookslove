package com.books.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.books.model.Book;
import com.books.model.Category;
import com.books.services.BookService;
@Controller
public class BookController {
	@Autowired
private BookService bookService;

public BookService getBookService() {
	return bookService;
}

public void setBookService(BookService bookService) {
	this.bookService = bookService;
}
@RequestMapping("/getAllBooks")
public ModelAndView getAllProducts(){
	List<Book> books = bookService.getAllBooks();
	return new ModelAndView("bookList","books",books);
}

//@RequestMapping("/task1")
//public ModelAndView task1getAllProducts(){
//	List<Book> books = bookService.getAllBooks();
//	return new ModelAndView("task1","books",books);
//}

@RequestMapping("getBookByIsbn/{isbn}")
public ModelAndView getBookByIsbn(@PathVariable(value="isbn") int isbn){
	Book b=bookService.getBookByIsbn(isbn);
	return new ModelAndView("bookPage","bookObj",b);
}

@RequestMapping("admin/delete/{isbn}")
public String deleteBook(@PathVariable(value="isbn") int isbn){
	
	Path path=Paths.get("D:/eclipse workspace/bookslove/src/main/webapp/WEB-INF/resources/images/"+isbn+".png");
	if(Files.exists(path))
			{
		try {
					Files.delete(path);
			} 
		catch (IOException e)
		{
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
			}
	bookService.deleteBook(isbn);
//http://localhost:8080/appname/getAllBooks
return"redirect:/getAllBooks";
}

@RequestMapping(value="/admin/book/addBook", method=RequestMethod.GET)
public String getBookForm(Model model){
	Book book=new Book();
	Category category=new Category();
	category.setCid(1);//New Arrivals
	//set the category as 1 for the Book book
	book.setCategory(category);
	model.addAttribute("bookFormObj",book);
	return "bookForm";
	}

@RequestMapping(value="/admin/book/addBook", method=RequestMethod.POST)
public String addBook(@Valid@ModelAttribute(value="bookFormObj") Book book,BindingResult result){
	if(result.hasErrors())
		return "bookForm";
	bookService.addBook(book);
	MultipartFile bookImage= book.getBookImage();
	if(bookImage!=null&& !bookImage.isEmpty()){
	Path path=Paths.get("D:/eclipse workspace/bookslove/src/main/webapp/WEB-INF/resources/images/"+book.getIsbn()+".png");
	try {
		bookImage.transferTo(new File(path.toString()));	
	} catch (IllegalStateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	return "redirect:/booksListAngular";
}

@RequestMapping("/admin/book/editBook/{isbn}")
public ModelAndView getEditForm(@PathVariable(value="isbn")  int isbn){
	//First read the record which has to be updated
	//select * from bookapp where isbn=?
	//Populate the form with already existing value
	Book book=this.bookService.getBookByIsbn(isbn);
	return new ModelAndView("editBookForm","editBookObj",book);
}

@RequestMapping(value="/admin/book/editBook",method=RequestMethod.POST)
public String editBook(@ModelAttribute(value="editBookObj") Book book)
{
bookService.editBook(book);
return "redirect:/booksListAngular";
}

@RequestMapping("/login")
public String login(@RequestParam(value="error",required=false) String error,
		@RequestParam(value="logout",required=false) String logout, 
		Model model){
		if(error!=null)
	model.addAttribute("error","Invalid username and password");
	
	if(logout!=null)
		model.addAttribute("logout","You have logged out successfully");
	return "login";
}

							// For Angular Js Searching from Database it returns list of books
	@RequestMapping("/getBooksList")
	public @ResponseBody List<Book> getBooksListInJSON(){
		return  bookService.getAllBooks();
	}
	
	@RequestMapping("/booksListAngular")
	public String getBooks(){
		return "booksListAngular";
	}

}
