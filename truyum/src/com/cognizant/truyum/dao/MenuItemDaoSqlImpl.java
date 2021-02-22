package com.cognizant.truyum.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.cognizant.truyum.model.MenuItem;

public class MenuItemDaoSqlImpl {

	public ArrayList<MenuItem> getMenuItemListAdmin() {
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from menu_item");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				String active = rs.getString("active");
				Date dateOfLaunch = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				String free_delivery = rs.getString("free_delivery");

				boolean act = false;
				if (active.equalsIgnoreCase("yes")) {
					act = true;
				}
				boolean free = false;
				if (free_delivery.equalsIgnoreCase("yes")) {
					free = true;
				}
				MenuItem m1 = new MenuItem(id, name, price, act, dateOfLaunch, category, free);
				menuItemList.add(m1);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return menuItemList;

	}

	public ArrayList<MenuItem> getMenuItemListCustomer() {
		ArrayList<MenuItem> menuItemListCust = new ArrayList<MenuItem>();
		Connection connection = ConnectionHandler.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(
					"select * from menu_item where date_of_launch >= '2017-03-15' and active = 'yes'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				String active = rs.getString("active");
				Date dateOfLaunch = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				String free_delivery = rs.getString("free_delivery");

				boolean act = false;
				if (active.equalsIgnoreCase("yes")) {
					act = true;
				}
				boolean free = false;
				if (free_delivery.equalsIgnoreCase("yes")) {
					free = true;
				}
				MenuItem m1 = new MenuItem(id, name, price, act, dateOfLaunch, category, free);
				menuItemListCust.add(m1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return menuItemListCust;

	}

	public MenuItem getMenuItem(long menuItemId) {
		Connection connection = ConnectionHandler.getConnection();
		MenuItem getMenu = null;
		try {

			PreparedStatement stmt = connection.prepareStatement("select * from menu_item" + "where id=?");
			stmt.setLong(1, menuItemId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				String active = rs.getString("active");
				Date dateOfLaunch = rs.getDate("date_of_launch");
				String category = rs.getString("category");
				String free_delivery = rs.getString("free_delivery");

				boolean act = false;
				if (active.equalsIgnoreCase("yes")) {
					act = true;
				}
				boolean free = false;
				if (free_delivery.equalsIgnoreCase("yes")) {
					free = true;
				}
				getMenu = new MenuItem(id, name, price, act, dateOfLaunch, category, free);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return getMenu;
	}

	public void modifyMenuItem(MenuItem menuItem) {
		Connection connection = ConnectionHandler.getConnection();
		long id = menuItem.getId();
		String name = menuItem.getName();
		float price = menuItem.getPrice();
		boolean active = menuItem.isActive();
		
		java.sql.Date date = (java.sql.Date) new Date(menuItem.getDateOfLaunch().getTime());
		String category = menuItem.getCategory();
		boolean free = menuItem.isFreeDelivery();
		String act;
		if (active == true) {
			act = "Yes";
		} else {
			act = "No";
		}
		String fd;
		if (free == true) {
			fd = "Yes";
		} else {
			fd = "No";
		}
		String query = "update menu_item\r\n"
				 +"set name = ?,price = ?,active = ?,date_of_launch = ?,category = ?,free_delivery = ?\r\n"
				 +"where id = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setFloat(2, price);
			stmt.setString(3, act);
			stmt.setDate(4, date);
			stmt.setString(5, category);
			stmt.setString(6, fd);
			stmt.setLong(7, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
