package winereviews.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.model.*;

public class ProvinceDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static ProvinceDao instance = null;
    protected ProvinceDao() {
        connectionManager = new ConnectionManager();
    }
    public static ProvinceDao getInstance() {
        if(instance == null) {
            instance = new ProvinceDao();
        }
        return instance;
    }

    /**
     * Save the Province instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Province create(Province province) throws SQLException {
        String insertProvince = "INSERT INTO Province(ProvinceName) VALUES(?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertProvince);
            insertStmt.setString(1, province.getProvinceName());
            insertStmt.executeUpdate();

            return province;
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

    public Province delete(Province province) throws SQLException {
        String deleteProvince = "DELETE FROM Province WHERE ProvinceName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteProvince);
            deleteStmt.setString(1, province.getProvinceName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Province instance.
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
     * Get the Province record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Province instance.
     */
    public Province getProvinceFromProvinceName(String provinceName) throws SQLException {
        String selectProvince = "SELECT ProvinceName FROM Province WHERE ProvinceName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectProvince);
            selectStmt.setString(1, provinceName);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                String resultProvinceName = results.getString("ProvinceName");
                Province Province = new Province(resultProvinceName);
                return Province;
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

    public List<Province> getProvinces() throws SQLException {
        List<Province> provinces = new ArrayList<>();
        String selectProvince = "SELECT * from Province";
        Connection connection = null;
        PreparedStatement selectStatment = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStatment = connection.prepareStatement(selectProvince);
            results = selectStatment.executeQuery();
            while(results.next()) {

                String provinceName = results.getString("ProvinceName");
                Province province = new Province(provinceName);
                provinces.add(province);
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
        return provinces;
    }
}
