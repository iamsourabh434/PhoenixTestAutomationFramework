package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManger;
import com.database.model.CustomerDBModel;

import io.qameta.allure.Step;

public class CustomerDao {
// executing the query for the tr_customer table . which will get the information of the customer.
	private static final Logger LOGGER = LogManager.getLogger(CustomerAddressDao.class);
	private static final String CUSTOMER_DETAIL_QUERY = """
			SELECT * from tr_customer WHERE id =?
			""";
	private CustomerDao() {
		
	}
	@Step("Getting customer information from database for specific customer id")
	public static CustomerDBModel getCustomerInfo(int customerId) {
		CustomerDBModel customerDBModel = null;
		try {
			LOGGER.info("Getting the connection from the Database Manager");
			Connection conn = DatabaseManger.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_DETAIL_QUERY);
			preparedStatement.setInt(1, customerId);
			LOGGER.info("Executing the query {} ",CUSTOMER_DETAIL_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getString("first_name"));
				System.out.println(resultSet.getString("email_id"));

				customerDBModel = new CustomerDBModel(
						resultSet.getInt("id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("mobile_number"),
						resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"),
						resultSet.getString("email_id_alt"),
						resultSet.getInt("tr_customer_address_id")
						);
			}
		} catch (SQLException e) {
			LOGGER.error("cannot convert resultset to the CustomerDBModel bean",e);
			System.err.print(e.getMessage());
		}

		return customerDBModel;
	}
}
