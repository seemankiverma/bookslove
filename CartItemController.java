package com.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.books.model.Book;
import com.books.model.Cart;
import com.books.model.CartItem;
import com.books.model.Customer;
import com.books.services.BookService;
import com.books.services.CartItemService;
import com.books.services.CartService;
import com.books.services.CustomerServices;

@Controller
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CustomerServices customerServices;
	@Autowired
	private BookService bookServices;
	@Autowired
	private CartService cartService;
	
	
	
	public CartService getCartService() {
		return cartService;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	public CartItemService getCartItemService() {
		return cartItemService;
	}
	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	public CustomerServices getCustomerServices() {
		return customerServices;
	}
	public void setCustomerServices(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}
	public BookService getBookServices() {
		return bookServices;
	}
	public void setBookServices(BookService bookServices) {
		this.bookServices = bookServices;
	}

	@RequestMapping("/cart/add/{isbn}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable(value = "isbn") int isbn){
		//Is to get the username of the customer
		//User object contains details about the user -username , password, activeuser or not
		User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username= user.getUsername();
		Customer customer=customerServices.getCustomerByUsername(username);
		System.out.println("Customer is " + customer.getCustomerEmail() );
		Cart cart=customer.getCart();	
		
		List<CartItem> cartItems=cart.getCartItem();
		Book book=bookServices.getBookByIsbn(isbn);
		for(int i=0;i<cartItems.size();i++){
			CartItem cartItem=cartItems.get(i);
			if(book.getIsbn()==cartItem.getBook().getIsbn()){
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getBook().getPrice());
				cartItemService.addCartItem(cartItem);
				return;
			}
		}
		CartItem cartItem=new CartItem();
		cartItem.setQuantity(1);
		cartItem.setBook(book);
		cartItem.setTotalPrice(book.getPrice() * 1);
		cartItem.setCart(cart);
		cartItemService.addCartItem(cartItem);
	}
	
	@RequestMapping("/cart/removecartitem/{cartItemId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeCartItem(
		@PathVariable(value="cartItemId") int cartItemId){
		cartItemService.removeCartItem(cartItemId);
	}

	@RequestMapping("/cart/removeAllItems/{cartId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeAllCartItems(@PathVariable(value="cartId") int cartId){
		Cart cart=cartService.getCartByCartId(cartId);
		cartItemService.removeAllCartItems(cart);
	}

}
