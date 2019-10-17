package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.ConnectionFactory;



public class Main {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection con = null;
		PreparedStatement stm = null;
		
		try {
			con = ConnectionFactory.getConnection();
			stm = con.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+"VALUES "
					+"(?,?,?,?,?)");
			
			stm.setString(1, "Carl Purple");
			stm.setString(2, "carl@gmail.com");
			stm.setDate(3, new Date(sdf.parse("10/16/2019").getTime()) );
			stm.setDouble(4, 3000.0);
			stm.setInt(5, 4);
			
			int rowAffected = stm.executeUpdate();
			System.out.println("done! rows affected: " + rowAffected);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.closeConnection(con, stm);
		}
		
	}

}
