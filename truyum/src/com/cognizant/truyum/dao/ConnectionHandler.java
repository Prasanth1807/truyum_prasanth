package com.cognizant.truyum.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {

	public static Connection getConnection() {

		Connection connection = null;

		Properties prop = new Properties();
		try {
			prop.load(new FileReader("F:\\892954\\truyum\\src\\Connection.properties"));
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/truyum", prop);
			if (connection != null) {

				System.out.println("connected to database");

			}
		} catch (IOException e) {
			System.out.println("Not connected to the database");
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Connection Properties file not found");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

}
