package com.revature.testdriver;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;
		Reimbursement r = new Reimbursement();
		r.setReimbId(28);
		r.setReimbResolver(3);
		r.setReimbStatusId(1);
		// User loggedInUser = userDao.findByUsernameAndPassword("admin", "admin");

		// System.out.println(reimbDao.findAll());

		// System.out.println("The seperator");
		System.out.println(reimbDao.update(r));

		// System.out.println(reimbDao.findByAuthorId(3));
		System.out.println();

		/*
		 * System.out.println("just saying"); //System.out.println(userDao.findAll());
		 * System.out.println(userDao.findByUsernameAndPassword("admin", "admin"));
		 */
	}

}
