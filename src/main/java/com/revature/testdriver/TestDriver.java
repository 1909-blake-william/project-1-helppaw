package com.revature.testdriver;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class TestDriver {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDao userDao = UserDao.currentImplementation;
		
		User loggedInUser = userDao.findByUsernameAndPassword("admin", "admin");
		
		System.out.println(loggedInUser);
		
		/*System.out.println("just saying");
		//System.out.println(userDao.findAll());
		System.out.println(userDao.findByUsernameAndPassword("admin", "admin"));
	*/
	}

}
