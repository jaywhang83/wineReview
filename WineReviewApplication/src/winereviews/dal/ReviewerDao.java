package winereviews.dal;

import winereviews.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewerDao {

  protected ConnectionManager connectionManager;

  private static ReviewerDao instance = null;
  protected ReviewerDao() {
    connectionManager = new ConnectionManager();
  }

  public static ReviewerDao getInstance() {
    if (instance == null) {
      instance = new ReviewerDao();
    }
    return instance;
  }

  public Reviewer create(Reviewer reviewer) throws SQLException {
    String insertReviewer =
        "INSERT INTO Reviewer (ReviewerName, TwitterHandle) VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReviewer);

      insertStmt.setString(1, reviewer.getReviewerName());
      insertStmt.setString(2, reviewer.getTwitterHandle());

      insertStmt.executeUpdate();

      return reviewer;
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

  public Reviewer getReviewerByName(String reviewerName) throws SQLException {
    String selectReviewer = "SELECT * FROM Reviewer WHERE ReviewerName=?";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectReviewer);
      selectStatment.setString(1, reviewerName);
      results = selectStatment.executeQuery();
      if (results.next()) {
        String resultReviewerName = results.getString("ReviewerName");
        String twitterHandle = results.getString("TwitterHandle");

        Reviewer reviewer = new Reviewer(resultReviewerName, twitterHandle);

        return reviewer;
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
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public Reviewer getReviewerByTwitterHandle(String twitterHandle) throws SQLException {
    String selectReviewer = "SELECT * FROM Reviewer WHERE TwitterHandle=?";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectReviewer);
      selectStatment.setString(1, twitterHandle);
      results = selectStatment.executeQuery();
      if (results.next()) {
        String reviewerName = results.getString("ReviewerName");
        String resultTwitterHandle = results.getString("TwitterHandle");

        Reviewer reviewer = new Reviewer(reviewerName, resultTwitterHandle);

        return reviewer;
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
      if (results != null) {
        results.close();
      }
    }
    return null;
  }

  public List<Reviewer> getReviewers() throws SQLException {
    List<Reviewer> reviewers = new ArrayList<>();
    String selectReviewer = "SELECT * from Reviewer";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectReviewer);
      results = selectStatment.executeQuery();
      while(results.next()) {

        String reviewerName = results.getString("ReviewerNAme");
        String twitterHandle = results.getString("TwitterHandle");
        Reviewer reviewer = new Reviewer(reviewerName, twitterHandle);
        reviewers.add(reviewer);
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
    return reviewers;
  }

    public Reviewer updateTwitterHandle(Reviewer reviewer) throws SQLException {
    String updateReviewer = "UPDATE Reviewer SET TwitterHandle=? WHERE ReviewerName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateReviewer);
      updateStmt.setString(1, reviewer.getTwitterHandle());
      updateStmt.setString(2, reviewer.getReviewerName());
      updateStmt.executeUpdate();

      return reviewer;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  public Reviewer delete(Reviewer reviewer) throws SQLException {
    String deleteReviewer = "DELETE FROM Reviewer WHERE ReviewerName=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReviewer);
      deleteStmt.setString(1, reviewer.getReviewerName());
      deleteStmt.executeUpdate();

      // Return null so the caller can no longer operate on the Reviewer instance.
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
}
