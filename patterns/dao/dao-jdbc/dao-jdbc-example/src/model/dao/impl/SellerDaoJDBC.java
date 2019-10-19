package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.ConnectionFactory;
import db.exceptions.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection con;

	public SellerDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Seller obj) {

	}

	@Override
	public void update(Seller obj) {

	}

	@Override
	public void deleteById(Integer id) {

	}
	
	

	@Override
	public Seller findById(Integer id) {
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = con.prepareStatement(
					"SELECT seller.*," + " department.Name as DepName" + " FROM seller INNER JOIN department"
							+ " ON Seller.DepartmentId = department.Id WHERE seller.Id = ?");

			stm.setInt(1, id);
			rs = stm.executeQuery();
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));

				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setDepartment(dep);
				return seller;
			}
			return null;
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, stm, rs);;
		}
	}

	@Override
	public List<Seller> findAll() {

		return null;
	}

}
