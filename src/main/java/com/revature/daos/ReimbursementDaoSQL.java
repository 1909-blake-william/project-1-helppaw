package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;

import com.revature.util.ConnectionUtil;

public class ReimbursementDaoSQL implements ReimbursementDao {

	private String selectStatement = "SELECT * FROM ers_reimbursement";

	Reimbursement extractReimbursement(ResultSet rs) throws SQLException {
		int reimbId = rs.getInt("reimb_id");
		double reimbAmount = rs.getDouble("reimb_amount");
		Timestamp reimbSubmitted = rs.getTimestamp("reimb_submitted");
		Timestamp reimbResolved = rs.getTimestamp("reimb_resolved");
		String reimbDescription = rs.getString("reimb_description");
		int reimbAuthor = rs.getInt("reimb_author");
		int reimbResolver = rs.getInt("reimb_resolver");
		int reimbStatusId = rs.getInt("reimb_status_id");
		int reimbTypeId = rs.getInt("reimb_type_id");
		return new Reimbursement(reimbId, reimbAmount, reimbSubmitted, reimbResolved, reimbDescription, reimbAuthor,
				reimbResolver, reimbStatusId, reimbTypeId);

	}

	@Override
	public int save(Reimbursement r) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ers_reimbursement (reimb_id, reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) "
					+ " VALUES (ers_reimbursement_id_seq.nextval , ?,current_timestamp,?,?,?,?)";

			CallableStatement cs = c.prepareCall(sql);

			cs.setDouble(1, r.getReimbAmount());
			cs.setString(2, r.getReimbDescription());
			cs.setInt(3, r.getReimbAuthor());
			cs.setInt(4, r.getReimbStatusId());
			cs.setInt(5, r.getReimbTypeId());

			return cs.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Reimbursement> findAll() {
		try (Connection c = ConnectionUtil.getConnection()) {
			System.out.println("test");

			String sql = selectStatement + " ORDER BY reimb_id";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}

			return reimbursements;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> findByAuthorId(int reimbAuthor) {
		try (Connection c = ConnectionUtil.getConnection()) {
			System.out.println("test");

			String sql = selectStatement + " WHERE reimb_author = ? ORDER BY reimb_id";

			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, reimbAuthor);

			ResultSet rs = ps.executeQuery();

			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractReimbursement(rs));
			}

			return reimbursements;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement findByStatusId(int reimbStatusId) {
		try (Connection c = ConnectionUtil.getConnection()) {
			/*
			 * log.debug("attempting to find pokemon by id from DB"); try (Connection c =
			 * ConnectionUtil.getConnection()) {
			 * 
			 * String sql = "SELECT * FROM pokemon_joined_data " + "WHERE pokemon_id = ? ";
			 * 
			 * PreparedStatement ps = c.prepareStatement(sql); ps.setInt(1, id);
			 * 
			 * ResultSet rs = ps.executeQuery(); if (rs.next()) { return extractPokemon(rs);
			 * } else { return null; }
			 * 
			 * } catch (SQLException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); return null; } }
			 */
			/*
			 * String sql = "SELECT * FROM ers_reimbursement r " +
			 * "LEFT JOIN ers_reimbursement_type t ON (r.reimbursement_type_id = t.reimbursement_type_id) "
			 * + "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_user_id)";
			 */
			String sql = "SELECT * FROM ers_reimbursement " + "WHERE reimb_status_id = ? ";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, reimbStatusId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractReimbursement(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement findById(int reimbId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release(int pokemonId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reimbursement> findByAuthorName(String name) {

		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "Select * FROM ers_reimbursement r"
					+ "LEFT JOIN ers_reimbursement_type t ON (r.reimbursement_type = t.reimb_type) "
					+ "LEFT JOIN ers_users u ON (r.reimb_author = u.ers_user_id) " + "WHERE username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursement = new ArrayList<>();
			while (rs.next()) {
				reimbursement.add(extractReimbursement(rs));
			}

			return reimbursement;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int update(Reimbursement r) {
		try (Connection c = ConnectionUtil.getConnection()) {

			/*
			 * UPDATE ers_reimbursement SET reimb_resolved = current_timestamp,
			 * reimb_status_id = 2, reimb_resolver = 3 WHERE reimb_id = 28;
			 */
			String sql = " UPDATE ers_reimbursement"
					+ " SET reimb_resolved = current_timestamp, reimb_status_id = ?, reimb_resolver = ?"
					+ " WHERE reimb_id = ?";

			CallableStatement cs = c.prepareCall(sql);

			cs.setInt(1, r.getReimbStatusId());
			cs.setInt(2, r.getReimbResolver());
			cs.setInt(3, r.getReimbId());
			System.out.println("im working...");
			return cs.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	/*
	 * 
	 * @Override public Pokemon findById(int id) {
	 * log.debug("attempting to find pokemon by id from DB"); try (Connection c =
	 * ConnectionUtil.getConnection()) {
	 * 
	 * String sql = "SELECT * FROM pokemon_joined_data " + "WHERE pokemon_id = ? ";
	 * 
	 * PreparedStatement ps = c.prepareStatement(sql); ps.setInt(1, id);
	 * 
	 * ResultSet rs = ps.executeQuery(); if (rs.next()) { return extractPokemon(rs);
	 * } else { return null; }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); return null; } }
	 * 
	 * @Override public void release(int pokemonId) {
	 * log.debug("attempting to find pokemon by id from DB"); try (Connection c =
	 * ConnectionUtil.getConnection()) {
	 * 
	 * String sql = "DELETE FROM pokemon WHERE pokemon_id = ? ";
	 * 
	 * PreparedStatement ps = c.prepareStatement(sql); ps.setInt(1, pokemonId);
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 * 
	 * @Override public List<Pokemon> findByName(String name) {
	 * log.debug("attempting to find pokemon by name from DB"); try (Connection c =
	 * ConnectionUtil.getConnection()) {
	 * 
	 * String sql = "SELECT * FROM pokemon p " +
	 * "LEFT JOIN pokemon_types t ON (p.pokemon_type_id = t.pokemon_types_id) " +
	 * "LEFT JOIN pokemon_users u ON (p.trainer = u.user_id) " +
	 * "WHERE pokemon_name = ?";
	 * 
	 * PreparedStatement ps = c.prepareStatement(sql); ps.setString(1, name);
	 * 
	 * ResultSet rs = ps.executeQuery(); List<Pokemon> pokemon = new ArrayList<>();
	 * while (rs.next()) { pokemon.add(extractPokemon(rs)); }
	 * 
	 * return pokemon;
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); return null; } }
	 * 
	 * @Override public List<Pokemon> findByTypeId(int typeId) {
	 * log.debug("attempting to find pokemon by type from DB"); try (Connection c =
	 * ConnectionUtil.getConnection()) {
	 * 
	 * String sql = "SELECT * FROM pokemon p " +
	 * "LEFT JOIN pokemon_types t ON (p.pokemon_type_id = t.pokemon_types_id) " +
	 * "LEFT JOIN pokemon_users u ON (p.trainer = u.user_id) " +
	 * "WHERE pokemon_type_id = ?";
	 * 
	 * PreparedStatement ps = c.prepareStatement(sql); ps.setInt(1, typeId);
	 * 
	 * ResultSet rs = ps.executeQuery(); List<Pokemon> pokemon = new ArrayList<>();
	 * while (rs.next()) { pokemon.add(extractPokemon(rs)); }
	 * 
	 * return pokemon;
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); return null; } }
	 * 
	 * @Override public List<Pokemon> findByTrainerName(String name) {
	 * log.debug("attempting to find pokemon by trainer name from DB"); try
	 * (Connection c = ConnectionUtil.getConnection()) {
	 * 
	 * String sql = "SELECT * FROM pokemon p " +
	 * "LEFT JOIN pokemon_types t ON (p.pokemon_type_id = t.pokemon_types_id) " +
	 * "LEFT JOIN pokemon_users u ON (p.trainer = u.user_id) " +
	 * "WHERE username = ?";
	 * 
	 * PreparedStatement ps = c.prepareStatement(sql); ps.setString(1, name);
	 * 
	 * ResultSet rs = ps.executeQuery(); List<Pokemon> pokemon = new ArrayList<>();
	 * while (rs.next()) { pokemon.add(extractPokemon(rs)); }
	 * 
	 * return pokemon;
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); return null; } }
	 * 
	 */

}
