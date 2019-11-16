package com.revature.testdriver;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.UserDao;
import com.revature.models.User;

public class TestDriver {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;
		
		//User loggedInUser = userDao.findByUsernameAndPassword("admin", "admin");
		
		System.out.println(reimbDao.findAll());
		
		System.out.println("The seperator");

		
		System.out.println(reimbDao.findByAuthorId(3));
		
		
		/*System.out.println("just saying");
		//System.out.println(userDao.findAll());
		System.out.println(userDao.findByUsernameAndPassword("admin", "admin"));
	*/
	}

}
