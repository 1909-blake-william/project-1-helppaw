package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {

	User extractUser(ResultSet rs) throws SQLException {
		int rsErsUserId = rs.getInt("ers_user_id");
		String rsUsername = rs.getString("username");
		String rsPassword = rs.getString("password");
		String rsUserFirstName = rs.getString("user_first_name");
		String rsUserLastName = rs.getString("user_last_name");
		String rsUserEmail = rs.getString("user_email");
		int rsUserRoleId = rs.getInt("user_role_id");
		return new User(rsErsUserId, rsUsername, rsPassword, rsUserFirstName, rsUserLastName, rsUserEmail,
				rsUserRoleId);
	}

	@Override
	public int save(User u) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ers_users (ers_user_id, username, password, user_first_name, user_last_name, user_email, user_role_id) "
					+ " VALUES (ers_users_id_seq.nextval ,?,?,?,?,?,?)";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getUserFirstName());
			ps.setString(4, u.getUserLastName());
			ps.setString(5, u.getUserEmail());
			ps.setInt(6, u.getUserRoleId());

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> findAll() {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ers_users";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				users.add(extractUser(rs));
			}

			return users;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	public User findByUsernameAndPassword(String username, String password) {
		try (Connection c = ConnectionUtil.getConnection()) {

			Statement statement = c.createStatement();

			// String sql = "SELECT * FROM ers_users u"
			// + "LEFT JOIN ers_user_roles r ON (u.user_role_id = r.ers_user_role_id) "
			// + "WHERE username = ? AND password = ?";

			String sql = "SELECT * FROM ers_users WHERE username = '" + username + "' AND password = '" + password
					+ "'";
			ResultSet rs = statement.executeQuery(sql);

			if (rs.next()) {
			//	int id = rs.getInt("user_id");
			//	String rsUsername = rs.getString("username");
			//String rsPassword = rs.getString("password");

				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ers_users WHERE username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			User u = null;
			if (rs.next()) {
				u = extractUser(rs);
			}

			return u;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findById(int ersUserId) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ers_users WHERE ers_user_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, ersUserId);

			ResultSet rs = ps.executeQuery();

			User u = null;
			if (rs.next()) {
				u = extractUser(rs);
			}

			return u;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}
}