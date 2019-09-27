package winereviews.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.model.*;

public class WineryDao {
protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static WineryDao instance = null;
	protected WineryDao() {
		connectionManager = new ConnectionManager();
	}
	public static WineryDao getInstance() {
		if(instance == null) {
			instance = new WineryDao();
		}
		return instance;
	}

	/**
	 * Save the Winery instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Winery create(Winery winery) throws SQLException {
		String insertWinery = "INSERT INTO Wineries(WineryName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWinery);
			insertStmt.setString(1, winery.getWineryName());
			insertStmt.executeUpdate();

			return winery;
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

	public Winery delete(Winery winery) throws SQLException {
		String deleteWinery = "DELETE FROM Wineries WHERE WineryName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWinery);
			deleteStmt.setString(1, winery.getWineryName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Winery instance.
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
	 * Get the Winery record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Winery instance.
	 */
	public Winery getWineryFromWineryName(String wineryName) throws SQLException {
		String selectWinery = "SELECT WineryName FROM Wineries WHERE WineryName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWinery);
			selectStmt.setString(1, wineryName);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultWineryName = results.getString("WineryName");
				Winery winery = new Winery(resultWineryName);
				return winery;
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

	public List<Winery> getWineries() throws SQLException {
	    List<Winery> wineries = new ArrayList<>();
	    String selectWinery = "SELECT * from Wineries";
	    Connection connection = null;
	    PreparedStatement selectStatment = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStatment = connection.prepareStatement(selectWinery);
	      results = selectStatment.executeQuery();
	      while(results.next()) {

	        String winneryName = results.getString("WineryName");
	        Winery winery = new Winery(winneryName);
	        wineries.add(winery);
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
	    return wineries;
	  }

}
