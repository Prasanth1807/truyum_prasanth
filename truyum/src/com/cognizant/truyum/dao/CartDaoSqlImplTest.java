package com.cognizant.truyum.dao;

import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImplTest {
	public static void main(String[] args) throws CartEmptyException {
		testAddCartItem();
		testGetAllCartItems();
		testRemoveCartItem();
	}

	private static void testRemoveCartItem() {
		CartDaoSqlImpl cartDao = new CartDaoSqlImpl();
		cartDao.removeCartItem(1, 2);
		try {
			
			cartDao.getAllCartItems(1);
			
		} catch (CartEmptyException e) {
			System.out.println("Cart is empty");
		
		}
			
	}

	private static void testGetAllCartItems() throws CartEmptyException {
		CartDao cartDao = new CartDaoSqlImpl();
		List<MenuItem> menuItemList = cartDao.getAllCartItems(1);
		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem);
		}
						
	}

	private static void testAddCartItem() throws CartEmptyException {
		CartDaoSqlImpl cartDao = new CartDaoSqlImpl();
		cartDao.addCartItem(1, 2);
		List<MenuItem> menuItemList = cartDao.getAllCartItems(1);
		for (MenuItem menuItem : menuItemList) {
			System.out.println(menuItem);
		}
		
		
	}

}
