package com.books.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.dao.CustomerOrderDao;
import com.books.model.Cart;
import com.books.model.CartItem;
import com.books.model.CustomerOrder;


@Service
public class CustomerOrderServiceImpl implements CustomerOrderService{
	 @Autowired
	    private CustomerOrderDao customerOrderDao;

	    @Autowired
	    private CartService cartService;

	    public void addCustomerOrder(CustomerOrder customerOrder){
	        customerOrderDao.addCustomerOrder(customerOrder);
	    }

	    public double getCustomerOrderGrandTotal(int cartId){
	        double grandTotal = 0;
	        Cart cart = cartService.getCartByCartId(cartId);
	        List<CartItem> cartItems = cart.getCartItem();

	        for (CartItem item : cartItems){
	            grandTotal += item.getTotalPrice();
	        }

	        return grandTotal;
	    }

}
