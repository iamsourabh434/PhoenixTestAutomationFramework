package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) throws SQLException {
		// Step 1 : establish the connection to the phoenix database

		Connection conn = DriverManager.getConnection("jdbc:mysql://64.227.160.186 :3306/SR_DEV", "srdev_ro_automation",
				"Srdev@123");

		Statement statement = conn.createStatement();
		
		

//		ResultSet resultSet2 = statement.executeQuery("SELECT first_name, last_name, mobile_number\r\n"
//				+ "FROM tr_customer\r\n" + "WHERE first_name IN (\r\n" + "    SELECT first_name\r\n"
//				+ "    FROM tr_customer\r\n" + "    GROUP BY first_name\r\n" + "    HAVING COUNT(*) = 1\r\n" + ");");
		
		ResultSet resultSet = statement.executeQuery("select first_name ,last_name ,mobile_number  from tr_customer where last_name ='kurhade'\r\n"
				+ "");

		while (resultSet.next()) {
			String first_name = resultSet.getString("first_name");
			String last_name = resultSet.getString("last_name");
			String mobile_number = resultSet.getString("mobile_number");
			System.out.println(first_name + "|" + last_name + "|" + mobile_number);

		}
		//conn.close();
	}

}
