package winereviews.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.model.TastingNotes;

public class TastingNotesDao {
protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static TastingNotesDao instance = null;
	protected TastingNotesDao() {
		connectionManager = new ConnectionManager();
	}
	public static TastingNotesDao getInstance() {
		if(instance == null) {
			instance = new TastingNotesDao();
		}
		return instance;
	}

	/**
	 * Save the TastingNotes instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public TastingNotes create(TastingNotes tastingNotes) throws SQLException {
		String insertTastingNotes = "INSERT INTO TastingNotes(Note) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTastingNotes);
			insertStmt.setString(1, tastingNotes.getNote());
			insertStmt.executeUpdate();

			return tastingNotes;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}


	public TastingNotes delete(TastingNotes tastingNotes) throws SQLException {
		String deleteTastingNotes = "DELETE FROM TastingNotes WHERE Note=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTastingNotes);
			deleteStmt.setString(1, tastingNotes.getNote());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the TastingNotes instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	/**
	 * Get the TastingNotes record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single TastingNotes instance.
	 */
	public TastingNotes getTastingNotesFromNote(String note) throws SQLException {
		String selectTastingNotes = "SELECT Note FROM TastingNotes WHERE Note=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTastingNotes);
			selectStmt.setString(1, note);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultNote = results.getString("Note");
				TastingNotes tastingNotes = new TastingNotes(resultNote);
				return tastingNotes;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<TastingNotes> getNotes() throws SQLException {
	    List<TastingNotes> tastingNotes = new ArrayList<>();
	    String selectTastingNotes = "SELECT * from TastingNotes";
	    Connection connection = null;
	    PreparedStatement selectStatment = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStatment = connection.prepareStatement(selectTastingNotes);
	      results = selectStatment.executeQuery();
	      while(results.next()) {

	        String note = results.getString("Note");
	        TastingNotes tastingNote = new TastingNotes(note);
	        tastingNotes.add(tastingNote);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	      throw e;
	    } finally {
	      if(connection != null) {
	        connection.close();
	      }
	      if(selectStatment != null) {
	        selectStatment.close();
	      }
	    }
	    return tastingNotes;
	  }

}
