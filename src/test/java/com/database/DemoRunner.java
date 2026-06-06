package com.database;

import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
		DatabaseMangerOLD.createConnection();
		long startTime = System.currentTimeMillis();
		
		for(int i = 1 ; i <=500;i++) {
		DatabaseMangerOLD.createConnection();
		DatabaseMangerOLD.createConnection();

	}
		long endTime = System.currentTimeMillis();
		System.out.println("Duration is - " +(endTime-startTime) + "ms");

}
}
