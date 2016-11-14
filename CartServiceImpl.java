package com.books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.dao.CartDao;
import com.books.model.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	
	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	
	public Cart getCartByCartId(int cartId) {
		// TODO Auto-generated method stub
		return cartDao.getCartByCartId(cartId);
	}

}
