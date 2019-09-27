package winereviews.dal;

import winereviews.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Region1Dao {

  protected ConnectionManager connectionManager;

  private static Region1Dao instance = null;
  protected Region1Dao() {
    connectionManager = new ConnectionManager();
  }

  public static Region1Dao getInstance() {
    if (instance == null) {
      instance = new Region1Dao();
    }
    return instance;
  }

  public Region1 create(Region1 region) throws SQLException {
    String insertRegion = "INSERT INTO Region1(Region) VALUES(?);";
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


  public Region1 getRegion(String region) throws SQLException {
    String selectRegion = "SELECT Region FROM Region1 WHERE Region=?;";
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
        Region1 region1 = new Region1(resultRegion);
        return region1;
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

  public List<Region1> getRegions() throws SQLException {
    List<Region1> regions = new ArrayList<>();
    String selectRegion = "SELECT * from Region1";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectRegion);
      results = selectStatment.executeQuery();
      while(results.next()) {

        String regionName = results.getString("Region");
        Region1 region = new Region1(regionName);
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
