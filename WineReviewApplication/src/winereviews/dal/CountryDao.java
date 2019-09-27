package winereviews.dal;

import winereviews.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import winereviews.dal.ConnectionManager;

public class CountryDao {
    protected ConnectionManager connectionManager;

    // Single pattern: instantiation is limited to one object.
    private static CountryDao instance = null;
    protected CountryDao() {
        connectionManager = new ConnectionManager();
    }
    public static CountryDao getInstance() {
        if(instance == null) {
            instance = new CountryDao();
        }
        return instance;
    }

    /**
     * Save the Country instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    public Country create(Country country) throws SQLException {
        String insertCountry = "INSERT INTO Countries(CountryName) VALUES(?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCountry);
            insertStmt.setString(1, country.getCountryName());
            insertStmt.executeUpdate();

            return country;
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


    public Country delete(Country country) throws SQLException {
        String deleteCountry = "DELETE FROM Countries WHERE CountryName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCountry);
            deleteStmt.setString(1, country.getCountryName());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Country instance.
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
     * Get the Country record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Country instance.
     */
    public Country getCountryFromCountryName(String countryName) throws SQLException {
        String selectCountry = "SELECT CountryName FROM Countries WHERE CountryName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCountry);
            selectStmt.setString(1, countryName);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
            results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                String resultCountryName = results.getString("CountryName");
                Country country = new Country(resultCountryName);
                return country;
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

    public List<Country> getCountries() throws SQLException {
        List<Country> countries = new ArrayList<>();
        String selectCountry = "SELECT * from Countries";
        Connection connection = null;
        PreparedStatement selectStatment = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStatment = connection.prepareStatement(selectCountry);
            results = selectStatment.executeQuery();
            while(results.next()) {

                String countryName = results.getString("CountryName");
                Country country = new Country(countryName);
                countries.add(country);
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
        return countries;
    }

}
