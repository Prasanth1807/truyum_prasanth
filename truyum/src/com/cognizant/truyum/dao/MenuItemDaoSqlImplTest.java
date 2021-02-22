package com.cognizant.truyum.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImplTest {

	public static void main(String[] args) {
		testGetMenuItemListAdmin();
		testGetMenuItemListCustomer();
		testModifyMenuItem();
	}

	public static void testGetMenuItemListAdmin() {

		MenuItemDaoSqlImpl menuItemDao = new MenuItemDaoSqlImpl();
		ArrayList<MenuItem> menuItems = menuItemDao.getMenuItemListAdmin();
		for (MenuItem Item : menuItems) {
			System.out.println(Item);
		}
	}

	public static void testGetMenuItemListCustomer() {
		MenuItemDaoSqlImpl menuItemDao = new MenuItemDaoSqlImpl();
		ArrayList<MenuItem> menuItems = menuItemDao.getMenuItemListCustomer();
		for (MenuItem Item : menuItems) {
			System.out.println(Item);
		}
	}

	public static void testModifyMenuItem() {

		MenuItemDaoSqlImpl menuItemdao = new MenuItemDaoSqlImpl();
		MenuItem menu = menuItemdao.getMenuItem(1);
		System.out.println(menu);

	}

}
