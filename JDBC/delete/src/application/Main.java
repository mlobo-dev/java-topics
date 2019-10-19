package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.ConnectionFactory;
import db.exceptions.DbException;
import db.exceptions.DbIntegrityException;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		Statement stm = null;

		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);

			stm = con.createStatement();


			int rows1 = stm.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			/*
			int x = 1;
			if(x<2) {
				throw new SQLException("Fake error");
				
			}*/
			
			int rows2 = stm.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			con.commit();
			
			System.out.println("Rows1: " + rows1);
			System.out.println("Rows2: " + rows2);

		} catch (SQLException e) {
			try {
				con.rollback();
				throw new DbException("Transaction rolled back! caused by: "+ e.getMessage());
			} catch (SQLException e1) {
				
				throw new DbException("Error trying to rollback caused by: "+e.getMessage());
			}
		} finally {
			ConnectionFactory.closeConnection(con, stm);
		}

	}

}
