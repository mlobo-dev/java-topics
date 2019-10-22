package application;

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
	}

}
