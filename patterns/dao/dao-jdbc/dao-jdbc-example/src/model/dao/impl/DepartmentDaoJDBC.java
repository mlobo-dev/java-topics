package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.ConnectionFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection con = ConnectionFactory.getConnection();
	
	public DepartmentDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Department dep) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("INSERT INTO department" + "(Name)" + "VALUES(?)");
			stm.setString(1, dep.getName());
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeStatement(stm);
		}

	}

	@Override
	public void update(Department dep) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			stm.setString(1, dep.getName());
			stm.setInt(2, dep.getId());
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeStatement(stm);
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
