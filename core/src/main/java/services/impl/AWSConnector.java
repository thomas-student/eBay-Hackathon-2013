package services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AWSConnector {

	public static Connection getConnection(){
		String url = "jdbc:mysql://meetdbinstance.cwb1vfnsmihi.us-east-1.rds.amazonaws.com/meet_db?user=meet_corporate";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {}
		return connection;
	}

}
