package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import db.exceptions.DbException;

public class ConnectionFactory {

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	private static Connection con = null;

	public static Connection getConnection() {

		if (con == null) {
			Properties props = loadProperties();
			String url = props.getProperty("url");
			try {
				con = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

		return con;
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeConnection(Connection con, Statement stm) {
		if (con != null) {
			try {
				con.close();
				stm.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeConnection(Connection con, Statement stm, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
				stm.close();
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeConnection(Statement stm, ResultSet rs) {
		if (con != null) {
			try {

				stm.close();
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeStatement(Statement stm) {
		if (con != null) {
			try {

				stm.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (con != null) {
			try {

				rs.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
