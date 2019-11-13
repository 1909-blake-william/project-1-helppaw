package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.daos.ReimbursementDao;

import com.revature.models.Reimbursement;

public class ReimbursementServlet extends HttpServlet {

	private ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		System.out.println("To context param: " + req.getServletContext().getInitParameter("To"));

		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Reimbursement> reimbursement;
		
		String authorName = req.getParameter("authorName");
	
		if (authorName != null) { //find by Author name
			reimbursement = reimbDao.findByAuthorName(authorName);
		} else { // find all
			reimbursement = reimbDao.findAll();
		}
			
	ObjectMapper om = new ObjectMapper();
	String json = om.writeValueAsString(reimbursement);
	
	resp.addHeader("content-type", "application/json");
	resp.getWriter().write(json);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read the reimbursement from the request body
		ObjectMapper om = new ObjectMapper();
		Reimbursement r = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);
		
		System.out.println(r);
		
		int id = reimbDao.save(r);
		r.setReimbId(id);
		
		String json = om.writeValueAsString(r);
		
		resp.getWriter().write(json);
		resp.setStatus(201); // created status code
	
	
	}

	
}
	
/*
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read the pokemon from the request body
		ObjectMapper om = new ObjectMapper();
		Pokemon p = (Pokemon) om.readValue(req.getReader(), Pokemon.class);

		System.out.println(p);

		int id = pokeDao.save(p);
		p.setId(id);

		String json = om.writeValueAsString(p);

		resp.getWriter().write(json);
		resp.setStatus(201); // created status code

	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");
		int id = Integer.parseInt(idString);
		pokeDao.release(id);
	}
}
*/