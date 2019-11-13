package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.daos.ReimbursementDaoSQL;

public interface ReimbursementDao {

	ReimbursementDao currentImplementation = new ReimbursementDaoSQL();

	/**
	 * used to save a new reimbursements
	 * 
	 * @param r the reimbursement to be created
	 * @return the generated id for the reimbursement
	 */
	int save(Reimbursement r);

	List<Reimbursement> findAll();

	List<Reimbursement> findByAuthorName(String name);

	List<Reimbursement> findByAuthorId(int reimbAuthor);

	Reimbursement findByStatusId(int reimbStatusId);

	Reimbursement findById(int reimbId);

	void release(int pokemonId);

}
