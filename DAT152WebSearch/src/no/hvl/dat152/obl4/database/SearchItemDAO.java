package no.hvl.dat152.obl4.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchItemDAO {

	public List<SearchItem> getSearchHistoryLastFive() {
		String sql = "SELECT * FROM SecOblig.History ORDER BY datetime DESC";
		// LIMIT 5
		// Derby lacks LIMIT
		List<SearchItem> result = new ArrayList<>();

		Connection        c  = null;
		PreparedStatement ps = null;
		ResultSet         r  = null;

		try {
			c  = DatabaseHelper.getConnection();
			ps = c.prepareStatement(sql);
			ps.setMaxRows(5);
			r = ps.executeQuery();

			while (r.next()) {
				SearchItem item = new SearchItem(
						r.getTimestamp("datetime"),
						r.getString("username"),
						r.getString("searchkey")
				);
				result.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
		} finally {
			DatabaseHelper.closeConnection(r, ps, c);
		}

		return result;
	}

	public List<SearchItem> getSearchHistoryForUser(String username) {
		String sql = "SELECT * FROM SecOblig.History WHERE username = ? ORDER BY datetime DESC";
		//  LIMIT 50
		// Derby lacks LIMIT
		List<SearchItem> result = new ArrayList<>();

		Connection        c  = null;
		PreparedStatement ps = null;
		ResultSet         r  = null;

		try {
			c  = DatabaseHelper.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setMaxRows(50);
			r = ps.executeQuery();

			while (r.next()) {
				SearchItem item = new SearchItem(
						r.getTimestamp("datetime"),
						r.getString("username"),
						r.getString("searchkey")
				);
				result.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
		} finally {
			DatabaseHelper.closeConnection(r, ps, c);
		}

		return result;
	}

	public List<SearchItem> getSearchHistoryForUser(String username, String sortkey) {
		String sql = "SELECT * FROM SecOblig.History WHERE username = ? ORDER BY ? ASC";
		//  LIMIT 50
		// Derby lacks LIMIT
		List<SearchItem> result = new ArrayList<>();

		Connection        c  = null;
		PreparedStatement ps = null;
		ResultSet         r  = null;

		try {
			c  = DatabaseHelper.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, sortkey);
			ps.setMaxRows(50);
			r = ps.executeQuery();

			while (r.next()) {
				SearchItem item = new SearchItem(
						r.getTimestamp("datetime"),
						r.getString("username"),
						r.getString("searchkey")
				);
				result.add(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
		} finally {
			DatabaseHelper.closeConnection(r, ps, c);
		}

		return result;
	}

	public void saveSearch(SearchItem search) {

		String sql = "INSERT INTO SecOblig.History VALUES (?, ?, ?)";

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet  r = null;

		try {
			c = DatabaseHelper.getConnection();
			ps = c.prepareStatement(sql);
			ps.setString(1, search.getDatetime().toString());
			ps.setString(2, search.getUsername());
			ps.setString(3, search.getSearchkey());
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DatabaseHelper.closeConnection(r, ps, c);
		}
	}

}
