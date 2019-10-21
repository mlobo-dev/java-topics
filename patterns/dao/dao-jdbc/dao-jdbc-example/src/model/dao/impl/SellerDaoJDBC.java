package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void insert(Seller seller) {
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = con.prepareStatement(
					"INSERT INTO seller" + "(Name, Email, BirthDate, BaseSalary,DepartmentId)" + "VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			stm.setString(1, seller.getName());
			stm.setString(2, seller.getEmail());
			stm.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			stm.setDouble(4, seller.getBaseSalary());
			stm.setInt(5, seller.getDepartment().getId());

			int rowsAffected = stm.executeUpdate();

			if (rowsAffected > 0) {
				rs = stm.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
			} else {
				throw new DbException("Unexpected error, no row affected.");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(stm, rs);
		}
	}

	@Override
	public void update(Seller seller) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " + "WHERE Id = ?");

			stm.setString(1, seller.getName());
			stm.setString(2, seller.getEmail());
			stm.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			stm.setDouble(4, seller.getBaseSalary());
			stm.setInt(5, seller.getDepartment().getId());
			stm.setInt(6, seller.getId());

			stm.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			ConnectionFactory.closeStatement(stm);
		}
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
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				return seller;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(stm, rs);

		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = con.prepareStatement(
					"SELECT seller.*,department.Name as DepName" + " FROM seller INNER JOIN department"
							+ " ON seller.DepartmentId = department.Id" + " ORDER BY Name");
			rs = stm.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = con.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");

			stm.setInt(1, department.getId());

			rs = stm.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(stm, rs);
		}

	}

}
