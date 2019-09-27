package winereviews.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.model.*;

public class VarietyDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static VarietyDao instance = null;

    protected VarietyDao() {
        connectionManager = new ConnectionManager();
    }

    public static VarietyDao getInstance() {
        if (instance == null) {
            instance = new VarietyDao();
        }
        return instance;
    }

    /**
     * Save the Variety instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Variety create(Variety variety) throws SQLException {
        String insertVariety = "INSERT INTO Variety(Grapes) VALUES(?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertVariety);
            insertStmt.setString(1, variety.getGrape());
            insertStmt.executeUpdate();

            return variety;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Variety delete(Variety variety) throws SQLException {
        String deleteVariety = "DELETE FROM Variety WHERE Grapes=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteVariety);
            deleteStmt.setString(1, variety.getGrape());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Variety instance.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

    /**
     * Get the Variety record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Variety instance.
     */
    public Variety getVarietyFromVarietyName(String grape) throws SQLException {
        String selectVariety = "SELECT Grapes FROM Variety WHERE Grapes=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectVariety);
            selectStmt.setString(1, grape);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if (results.next()) {
                String resultVariety = results.getString("Grapes");
                Variety variety = new Variety(resultVariety);
                return variety;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public List<Variety> getVarieties() throws SQLException {
        List<Variety> varieties = new ArrayList<>();
        String selectVariety = "SELECT * from Variety";
        Connection connection = null;
        PreparedStatement selectStatment = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStatment = connection.prepareStatement(selectVariety);
            results = selectStatment.executeQuery();
            while (results.next()) {

                String grape = results.getString("Grapes");
                Variety variety = new Variety(grape);
                varieties.add(variety);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStatment != null) {
                selectStatment.close();
            }
        }
        return varieties;
    }
}
