package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner_checkingOfHikariDatabaseManager {

	public static void main(String[] args) throws SQLException {
		Connection conn=DatabaseManger.getConnection();
		System.out.println(conn);

	}
}
