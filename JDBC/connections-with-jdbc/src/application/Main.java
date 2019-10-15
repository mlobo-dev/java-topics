package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.ConnectionFactory;



public class Main {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionFactory.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery("SELECT * FROM department");
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ":" + rs.getString("name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.closeConnection(con, stm, rs);
		}
	}

}
