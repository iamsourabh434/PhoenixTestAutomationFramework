package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManger;
import com.database.model.JobHeadModel;

import io.qameta.allure.Step;

public class JobHeadDao {
	
	private static final Logger LOGGER = LogManager.getLogger(JobHeadDao.class);
	private static final String JOB_HEAD_QUERY="""
			select * from tr_job_head 
			where tr_customer_id = ?
			""";
	private JobHeadDao() {
		
	}
	@Step("Retriving the Job head info for specific customer ID")
	public static JobHeadModel getDataFromJobHead(int tr_customer_id) {
		JobHeadModel jobHeadModel=null;
		try {
			LOGGER.info("Getting the connection from the Database Manager");
			Connection conn = DatabaseManger.getConnection();
			PreparedStatement ps = conn.prepareStatement(JOB_HEAD_QUERY);
			ps.setInt(1, tr_customer_id);
			LOGGER.info("Executing the query {} ",JOB_HEAD_QUERY);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				jobHeadModel = new JobHeadModel(rs.getInt("id"),
						rs.getString("job_number"),
						rs.getInt("tr_customer_id"), 
						rs.getInt("tr_customer_product_id"),
						rs.getInt("mst_service_location_id"),
						rs.getInt("mst_platform_id"),
						rs.getInt("mst_warrenty_status_id"),
						rs.getInt("mst_oem_id"));
				
			}
			
		} catch (SQLException e) {
			LOGGER.error("Cannot convert resultset to the JobHeadModel bean",e);
			System.err.println(e.getMessage());
		}
		return jobHeadModel;
	}

}
