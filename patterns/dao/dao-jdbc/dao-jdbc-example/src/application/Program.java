package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(2);
		System.out.println(seller);

		System.out.println();

		System.out.println("=== TEST 2: seller findByDepartment ===");
		Department department = new Department(2, null);

		List<Seller> list = sellerDao.findByDepartment(department);

		for (Seller s : list) {
			System.out.println(s);
		}

		System.out.println();

		System.out.println("=== TEST 3: seller findAll ===");
		list = sellerDao.findAll();
		for (Seller s : list) {
			System.out.println(s);
		}

		System.out.println("=== TEST 4: seller insert ===");
		Department dep = new Department(7, "Education");
		Seller s2 = new Seller(null, "Greg", "greg@mail", new Date(), 5000.0, department);
		sellerDao.insert(s2);
		System.out.println("Inserted! New ID: " + s2.getId());

		System.out.println("=== TEST 5: seller update ===");
		seller = sellerDao.findById(1);
		System.out.println("Original Name: " + seller.getName());
		seller.setName("Martha Waine");
		seller.setEmail("mw@gmail.com");
		System.out.println("New Name: " + seller.getName());
		sellerDao.update(seller);
		System.out.println("Update Successfuly");
	}

}
