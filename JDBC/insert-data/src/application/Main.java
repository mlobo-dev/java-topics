package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.ConnectionFactory;

public class Main {

	public static void main(String[] args) {
			Connection con = null;
			PreparedStatement st = null;
			
			try {
				con = ConnectionFactory.getConnection();
				
				st = con.prepareStatement(
				"UPDATE seller SET BaseSalary = BaseSalary + ? WHERE (DepartmentID = ?)");
						
				st.setDouble(1, 200.0);
				st.setInt(2, 2);
				
				int rowsAffected = st.executeUpdate();
				
				System.out.println("Done! rows Affected: " + rowsAffected);
				
			}catch(SQLException e) {
				e.printStackTrace();
			}

	}

}
