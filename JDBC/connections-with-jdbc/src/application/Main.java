package application;

import java.sql.Connection;

import db.DB;

public class Main {

	public static void main(String[] args) {
		
		Connection con = DB.getConnection();
		System.out.println("connection successfully");
		DB.closeConnection();
		System.out.println("Connection closed");
	}

}
