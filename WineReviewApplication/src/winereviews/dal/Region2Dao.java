package winereviews.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.model.*;

public class Region2Dao {
    protected ConnectionManager connectionManager;

    private static Region2Dao instance = null;
    protected Region2Dao() {
        connectionManager = new ConnectionManager();
    }

    public static Region2Dao getInstance() {
        if (instance == null) {
            instance = new Region2Dao();
        }
        return instance;
    }

    public Region2 create(Region2 region) throws SQLException {
        String insertRegion = "INSERT INTO Region2(Region) VALUES(?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRegion);
            insertStmt.setString(1, region.getRegion());
            insertStmt.executeUpdate();

            return region;
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


    public Region2 getRegion(String region) throws SQLException {
        String selectRegion = "SELECT Region FROM Region2 WHERE Region=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRegion);
            selectStmt.setString(1, region);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                String resultRegion= results.getString("Region");
                Region2 Region2 = new Region2(resultRegion);
                return Region2;
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

    public List<Region2> getRegions() throws SQLException {
        List<Region2> regions = new ArrayList<>();
        String selectRegion = "SELECT * from Region2";
        Connection connection = null;
        PreparedStatement selectStatment = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStatment = connection.prepareStatement(selectRegion);
            results = selectStatment.executeQuery();
            while(results.next()) {

                String regionName = results.getString("Region");
                Region2 region = new Region2(regionName);
                regions.add(region);
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
        return regions;
    }

}
