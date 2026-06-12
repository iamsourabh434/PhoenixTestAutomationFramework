package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManger;
import com.database.model.CustomerDBModel;

public class CustomerDao {
// executing the query for the tr_customer table . which will get the information of the customer.

	private static final String CUSTOMER_DETAIL_QUERY = """
			SELECT * from tr_customer WHERE id =319901
			""";

	public static CustomerDBModel getCustomerInfo() throws SQLException {
		Connection conn = DatabaseManger.getConnection();
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(CUSTOMER_DETAIL_QUERY);
		CustomerDBModel customerDBModel = null;
		while (resultSet.next()) {
			System.out.println(resultSet.getString("first_name"));
			System.out.println(resultSet.getString("email_id"));

			customerDBModel = new CustomerDBModel(resultSet.getString("first_name"), resultSet.getString("last_name"),
					resultSet.getString("mobile_number"), resultSet.getString("mobile_number_alt"),
					resultSet.getString("email_id"), resultSet.getString("email_id_alt"));
		}
		return customerDBModel;
	}
}
