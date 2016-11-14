package com.books.dao;

import com.books.model.Cart;
import com.books.model.CartItem;

public interface CartItemDao {
	void addCartItem(CartItem cartItem);
	void removeCartItem(int cartItemId);
	void removeAllCartItems(Cart cart); // confirm??

}
