package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		Department dep = new Department();
		DepartmentDao depDao = DaoFactory.createDepartmentDao();

		System.out.println("==== TEST 1 : Department insert ====");
		dep.setName("Music");
		depDao.insert(dep);
		System.out.println("Insert Successfuly");

		System.out.println();

		System.out.println("==== TEST 2 : Department update ====");
		dep.setName("Grafic Arts");
		dep.setId(6);
		depDao.update(dep);
		System.out.println("update Successfuly");

		System.out.println();

		System.out.println("==== TEST 4 : Department delete ====");

		dep.setId(8);
		depDao.deleteById(dep.getId());
		System.out.println("delete Successfuly");

		System.out.println();
		
		System.out.println("==== TEST 5 : Department delete ====");		
		System.out.println(depDao.findById(1));;
		System.out.println("findById Successfuly");
		
		System.out.println();
		
		System.out.println("==== TEST 6 : Department findAll ====");
		List<Department> depList = depDao.findAll();
		
		for (Department department : depList) {
			System.out.println("Dep Name: "+ department.getName());
		}
	}

}
