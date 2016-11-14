package com.books.services;

import com.books.model.Cart;
import com.books.model.CartItem;

public interface CartItemService {
	void addCartItem(CartItem cartItem);
	void removeCartItem(int cartItemId);
	void removeAllCartItems(Cart cart);

}
