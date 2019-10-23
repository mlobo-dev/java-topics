package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("DELETE FROM department WHERE Id = ?");

			stm.setInt(1, id);
			stm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeStatement(stm);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = con.prepareStatement("SELECT * FROM department WHERE Id = ?");

			stm.setInt(1, id);
			rs = stm.executeQuery();

			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeStatement(stm);
		}
		return null;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = con.prepareStatement("SELECT * FROM department ");

			rs = stm.executeQuery();
			List<Department> depList = new ArrayList<Department>();

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				depList.add(dep);

			}
			return depList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeStatement(stm);
		}
		return null;
	}

}
