package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.ConnectionFactory;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement stm = null;

		try {
			con = ConnectionFactory.getConnection();

			stm = con.prepareStatement("" + "DELETE FROM department " + "WHERE " + "ID = ? ");

			stm.setInt(1, 5);
			int rowsAffected = stm.executeUpdate();

			System.out.println("Done! rows Affected: " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, stm);
		}

	}

}
