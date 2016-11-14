package com.books.dao;

import java.io.IOException;

import com.books.model.Cart;

public interface CartDao {
	Cart getCartByCartId(int cartId);
	Cart validate(int cartId) throws IOException;

	void update(Cart cart);
}
