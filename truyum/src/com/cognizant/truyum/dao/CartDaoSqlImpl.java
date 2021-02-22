package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoSqlImpl implements CartDao {

	@Override
	public void addCartItem(long userId, long ItemId) {

		Connection con = ConnectionHandler.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into cart(item_id,user_id) values(?,?)");
			ps.setLong(1, ItemId);
			ps.setLong(2, userId);
			ps.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {

		Connection con = ConnectionHandler.getConnection();
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		Cart cart = new Cart(menuItemList, 0);
		MenuItem menuItem = null;

		try {
			PreparedStatement ps = con
					.prepareStatement("select * from menu_item m join cart c on m.id=c.item_id" + "where c.user_id=?;");
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				long id = rs.getInt("id");
				String name = rs.getString("Name");
				float price = rs.getInt("Price");
				String active = rs.getString("Active");
				Date date = rs.getDate("Date_of_Launch");
				String category = rs.getString("Category");
				String freeDelivery = rs.getString("Free_Delivery");
				boolean act = false;
				if (active.equalsIgnoreCase("Yes")) {
					act = true;
				}
				boolean free = false;
				if (freeDelivery.equalsIgnoreCase("Yes")) {
					free = true;
				}
				menuItem = new MenuItem(id, name, price, act, date, category, free);
				menuItemList.add(menuItem);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItemList;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {

		Connection con = ConnectionHandler.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from cart" + "where user_id=? and item_id=?;");
			ps.setLong(1, userId);
			ps.setLong(2, menuItemId);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}