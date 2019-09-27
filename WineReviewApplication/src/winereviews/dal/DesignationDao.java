package winereviews.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.model.*;

public class DesignationDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static DesignationDao instance = null;
    protected DesignationDao() {
        connectionManager = new ConnectionManager();
    }
    public static DesignationDao getInstance() {
        if(instance == null) {
            instance = new DesignationDao();
        }
        return instance;
    }

    /**
     * Save the Designation instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Designation create(Designation designation) throws SQLException {
        String insertDesignation = "INSERT INTO Designation(DesignationName) VALUES(?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertDesignation);
            insertStmt.setString(1, designation.getDesignationName());
            insertStmt.executeUpdate();

            return designation;
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


    public Designation delete(Designation designation) throws SQLException {
        String deleteDesignation = "DELETE FROM Designation WHERE DesignationName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteDesignation);
            deleteStmt.setString(1, designation.getDesignationName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Designation instance.
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
     * Get the Designation record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Designation instance.
     */
    public Designation getDesignationFromDesignationName(String designationName) throws SQLException {
        String selectDesignation = "SELECT DesignationName FROM Designation WHERE DesignationName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectDesignation);
            selectStmt.setString(1, designationName);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                String resultDesignationName = results.getString("DesignationName");
                Designation designation = new Designation(resultDesignationName);
                return designation;
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

    public List<Designation> getDestinations() throws SQLException {
        List<Designation> designations = new ArrayList<>();
        String selectDesignation = "SELECT * from Designation";
        Connection connection = null;
        PreparedStatement selectStatment = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStatment = connection.prepareStatement(selectDesignation);
            results = selectStatment.executeQuery();
            while(results.next()) {

                String designationName = results.getString("DesignationName");
                Designation designation = new Designation(designationName);
                designations.add(designation);
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
        return designations;
    }
}
