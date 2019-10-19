package model.dao;

import db.ConnectionFactory;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(ConnectionFactory.getConnection());
	}
}
