package com.bank.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectorFactory {
	public static Connection requestConnection() {
		Connection con=null;
		PreparedStatement ps=null;
		String url="jdbc:mysql://localhost:3306/bank";
		String user="root";
		String password="tiger";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
