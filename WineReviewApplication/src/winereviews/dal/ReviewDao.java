package winereviews.dal;

import java.util.HashSet;
import java.util.Set;
import winereviews.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReviewDao {

  protected ConnectionManager connectionManager;

  private static ReviewDao instance = null;

  protected ReviewDao() {
    connectionManager = new ConnectionManager();
  }

  public static ReviewDao getInstance() {
    if (instance == null) {
      instance = new ReviewDao();
    }
    return instance;
  }

  public List<Review> getGuidedReviews() throws SQLException {
    List<Review> reviews = new ArrayList<>();
    String selectReview = "SELECT * FROM Reviews WHERE ReviewId=?"
        + " OR ReviewId=?"
        + " OR ReviewId=?"
        + " OR ReviewId=?"
        + " OR ReviewId=?";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectReview);
      selectStatment.setInt(1, 91841); // Merlot
      selectStatment.setInt(2, 109333); // Chardonnay
      selectStatment.setInt(3, 56955); // Pinot
      selectStatment.setInt(4, 1556); // Cabernet Sauvignon
      selectStatment.setInt(5, 123545); // Syrah
      results = selectStatment.executeQuery();

      ReviewerDao reviewerDao = ReviewerDao.getInstance();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        Country country = new Country(results.getString("Country"));
        String description = results.getString("Description");
        Designation designation = new Designation(results.getString("Designation"));
        int points = results.getInt("Points");
        String price = results.getString("Price");
        Province province = new Province(results.getString("Province"));
        Region1 region1 = new Region1(results.getString("Region_1"));
        Region2 region2 = new Region2(results.getString("Region_2"));
        String reviewerName = results.getString("ReviewerName");
        String title = results.getString("Title");
        Variety variety = new Variety(results.getString("Variety"));
        Winery resultWinery = new Winery(results.getString("Winery"));

        Reviewer reviewer = reviewerDao.getReviewerByName(reviewerName);

        Review review = new Review(reviewId, country, description, designation,
            points, price, province, region1, region2, reviewer, title, variety, resultWinery);

        reviews.add(review);

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
    return reviews;
  }

  public List<Review> getReviewsByReviewer(Reviewer reviewer) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    String selectReview = "SELECT * FROM Reviews WHERE ReviewerName=? LIMIT 100";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    // hashMap that keeps track of parameter index and the value

    try {
      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectReview);
      selectStatment.setString(1, reviewer.getReviewerName());
      ReviewerDao reviewerDao = ReviewerDao.getInstance();
      results = selectStatment.executeQuery();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        Country resultCountry = new Country(results.getString("Country"));
        String description = results.getString("Description");
        Designation designation = new Designation(results.getString("Designation"));
        int points = results.getInt("Points");
        String price = results.getString("Price");
        Province province = new Province(results.getString("Province"));
        Region1 region1 = new Region1(results.getString("Region_1"));
        Region2 region2 = new Region2(results.getString("Region_2"));
        String reviewerName = results.getString("ReviewerName");
        String title = results.getString("Title");
        Variety resultVariety = new Variety(results.getString("Variety"));
        Winery winery = new Winery(results.getString("Winery"));

        Reviewer resultReviewer = reviewerDao.getReviewerByName(reviewerName);

        Review review = new Review(reviewId, resultCountry, description, designation,
            points, price, province, region1, region2, resultReviewer, title, resultVariety,
            winery);

        reviews.add(review);
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
    return reviews;
  }

  public List<Review> getReviews(String country, String minPrice, String maxPrice, int rating,
      String variety) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    // Have default value for rating. Default rating value will be passed in
    // Default minPrice will be passed in
    String selectReview = "SELECT * FROM Reviews WHERE Points > ? AND Price > ?";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    int parameterNumber = 2;
    // hashMap that keeps track of parameter index and the value
    Map<String, Integer> paramList = new HashMap<String, Integer>();

    try {
      AbstractMap.SimpleEntry<Integer, String> temp =
          helper(minPrice, maxPrice, country, variety, paramList, parameterNumber, selectReview);
      selectReview = temp.getValue();
      parameterNumber = temp.getKey();

      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectReview);
      selectStatment.setInt(1, rating);
      selectStatment.setInt(2, Integer.valueOf(minPrice));
      for (Map.Entry<String, Integer> param : paramList.entrySet()) {
        if (param.getKey() == maxPrice) {
          selectStatment.setInt(param.getValue(), Integer.valueOf(param.getKey()));
        } else {
          selectStatment.setString(param.getValue(), param.getKey());
        }
      }
      results = selectStatment.executeQuery();
      paramList.clear();
      ReviewerDao reviewerDao = ReviewerDao.getInstance();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        Country resultCountry = new Country(results.getString("Country"));
        String description = results.getString("Description");
        Designation designation = new Designation(results.getString("Designation"));
        int points = results.getInt("Points");
        String price = results.getString("Price");
        Province province = new Province(results.getString("Province"));
        Region1 region1 = new Region1(results.getString("Region_1"));
        Region2 region2 = new Region2(results.getString("Region_2"));
        String reviewerName = results.getString("ReviewerName");
        String title = results.getString("Title");
        Variety resultVariety = new Variety(results.getString("Variety"));
        Winery winery = new Winery(results.getString("Winery"));

        Reviewer reviewer = reviewerDao.getReviewerByName(reviewerName);

        Review review = new Review(reviewId, resultCountry, description, designation,
            points, price, province, region1, region2, reviewer, title, resultVariety, winery);

        reviews.add(review);
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
    return reviews;
  }

  public List<Review> getRecommendations(Map<String, String[]> favorites) {
    Set<String> notes = new HashSet<>();
    ReviewTastingNotesDao reviewTastingNotesDao = ReviewTastingNotesDao.getInstance();
    List<String> varieties = new ArrayList<>();

    for (Map.Entry<String, String[]> entry : favorites.entrySet()) {
      try {
        varieties.add(entry.getValue()[0]);
        List<ReviewTastingNotes> tmp = reviewTastingNotesDao
            .getReviewTastingNotesByReviewId(Integer.parseInt(entry.getKey()));
        for (ReviewTastingNotes rtn : tmp) {
          notes.add(rtn.getNote());
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    List<String> notes_list = new ArrayList<>();
    notes_list.addAll(notes);

    List<Review> reviews = new ArrayList<>();
    for (String variety : varieties) {
      try {
        reviews.addAll(getReviewsByNote_Limit(null, "0", "1000", 0, variety, notes_list));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return reviews;
  }


  public List<Review> getReviewsByNote(String state, String minPrice, String maxPrice, int rating,
      String variety, List<String> notes) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    // Have default value for rating. Default rating value will be passed in
    // Default minPrice will be passed in
    String selectReview = "SELECT * FROM Reviews INNER JOIN ReviewTastingNotes "
        + "ON Reviews.ReviewId = ReviewTastingNotes.ReviewId "
        + "WHERE Points > ? AND Price > ?";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    int parameterNumber = 2;
    Map<String, Integer> paramList = new HashMap<String, Integer>();

    try {
      AbstractMap.SimpleEntry<Integer, String> temp =
          helper(minPrice, maxPrice, state, variety, paramList, parameterNumber, selectReview);
      selectReview = temp.getValue();
      parameterNumber = temp.getKey();

      if (notes.size() == 1) {
        selectReview += " AND Note = ?";
        parameterNumber += 1;
        paramList.put(notes.get(0), parameterNumber);
      } else if (notes.size() > 1) {
        String noteQueries = " AND (Note = ?";
        parameterNumber += 1;
        paramList.put(notes.get(0), parameterNumber);
        for (int i = 1; i < notes.size(); i++) {
          noteQueries += " OR Note = ?";
          parameterNumber += 1;
          paramList.put(notes.get(i), parameterNumber);
        }
        noteQueries += ")";
        selectReview += noteQueries;
      }
      connection = connectionManager.getConnection();
      selectStatment = connection.prepareStatement(selectReview);
      selectStatment.setInt(1, rating);
      selectStatment.setInt(2, Integer.valueOf(minPrice));
      for (Map.Entry<String, Integer> param : paramList.entrySet()) {
        if (param.getKey() == maxPrice) {
          selectStatment.setInt(param.getValue(), Integer.valueOf(param.getKey()));
        } else {
          selectStatment.setString(param.getValue(), param.getKey());
        }
      }
      results = selectStatment.executeQuery();
      paramList.clear();

      ReviewerDao reviewerDao = ReviewerDao.getInstance();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        Country resultCountry = new Country(results.getString("Country"));
        String description = results.getString("Description");
        Designation designation = new Designation(results.getString("Designation"));
        int points = results.getInt("Points");
        String price = results.getString("Price");
        Province province = new Province(results.getString("Province"));
        Region1 region1 = new Region1(results.getString("Region_1"));
        Region2 region2 = new Region2(results.getString("Region_2"));
        String reviewerName = results.getString("ReviewerName");
        String title = results.getString("Title");
        Variety resultVariety = new Variety(results.getString("Variety"));
        Winery winery = new Winery(results.getString("Winery"));

        Reviewer reviewer = reviewerDao.getReviewerByName(reviewerName);

        Review review = new Review(reviewId, resultCountry, description, designation,
            points, price, province, region1, region2, reviewer, title, resultVariety, winery);

        reviews.add(review);
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
    return reviews;
  }

  public List<Review> getReviewsByNote_Limit(String state, String minPrice, String maxPrice,
      int rating,
      String variety, List<String> notes) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    // Have default value for rating. Default rating value will be passed in
    // Default minPrice will be passed in
    String selectReview = "SELECT * FROM Reviews INNER JOIN ReviewTastingNotes "
        + "ON Reviews.ReviewId = ReviewTastingNotes.ReviewId "
        + "WHERE Points > ? AND Price > ?";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    int parameterNumber = 2;
    Map<String, Integer> paramList = new HashMap<String, Integer>();

    try {
      AbstractMap.SimpleEntry<Integer, String> temp =
          helper(minPrice, maxPrice, state, variety, paramList, parameterNumber, selectReview);
      selectReview = temp.getValue();
      parameterNumber = temp.getKey();

      if (notes.size() == 1 && notes.get(0) != "") {
        selectReview += " AND Note = ?";
        parameterNumber += 1;
        paramList.put(notes.get(0), parameterNumber);
      } else if (notes.size() > 1) {
        String noteQueries = " AND (Note = ?";
        parameterNumber += 1;
        paramList.put(notes.get(0), parameterNumber);
        for (int i = 1; i < notes.size(); i++) {
          noteQueries += " OR Note = ?";
          parameterNumber += 1;
          paramList.put(notes.get(i), parameterNumber);
        }
        noteQueries += ")";
        selectReview += noteQueries;
      }
      connection = connectionManager.getConnection();
      selectReview += " LIMIT 100";
      selectStatment = connection.prepareStatement(selectReview);
      selectStatment.setInt(1, rating);
      selectStatment.setInt(2, Integer.valueOf(minPrice));
      for (Map.Entry<String, Integer> param : paramList.entrySet()) {
        if (param.getKey() == maxPrice) {
          selectStatment.setInt(param.getValue(), Integer.valueOf(param.getKey()));
        } else {
          selectStatment.setString(param.getValue(), param.getKey());
        }
      }
      results = selectStatment.executeQuery();
      paramList.clear();

      ReviewerDao reviewerDao = ReviewerDao.getInstance();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        Country resultCountry = new Country(results.getString("Country"));
        String description = results.getString("Description");
        Designation designation = new Designation(results.getString("Designation"));
        int points = results.getInt("Points");
        String price = results.getString("Price");
        Province province = new Province(results.getString("Province"));
        Region1 region1 = new Region1(results.getString("Region_1"));
        Region2 region2 = new Region2(results.getString("Region_2"));
        String reviewerName = results.getString("ReviewerName");
        String title = results.getString("Title");
        Variety resultVariety = new Variety(results.getString("Variety"));
        Winery winery = new Winery(results.getString("Winery"));

        Reviewer reviewer = reviewerDao.getReviewerByName(reviewerName);

        Review review = new Review(reviewId, resultCountry, description, designation,
            points, price, province, region1, region2, reviewer, title, resultVariety, winery);

        reviews.add(review);
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
    return reviews;
  }


  public List<Review> getReviewsByNote_Limit_Notes(String state, String minPrice, String maxPrice,
      int rating,
      String variety, List<String> notes) throws SQLException {
    List<Review> reviews = new ArrayList<>();
    // Have default value for rating. Default rating value will be passed in
    // Default minPrice will be passed in
    String selectReview = "SELECT * FROM Reviews INNER JOIN ReviewTastingNotes "
        + "ON Reviews.ReviewId = ReviewTastingNotes.ReviewId "
        + "WHERE Points > ? AND Price > ?";
    Connection connection = null;
    PreparedStatement selectStatment = null;
    ResultSet results = null;
    int parameterNumber = 2;
    Map<String, Integer> paramList = new HashMap<String, Integer>();

    try {
      AbstractMap.SimpleEntry<Integer, String> temp =
          helper(minPrice, maxPrice, state, variety, paramList, parameterNumber, selectReview);
      selectReview = temp.getValue();
      parameterNumber = temp.getKey();

      if (notes.size() == 1 && notes.get(0) != "") {
        selectReview += " AND Note = ?";
        parameterNumber += 1;
        paramList.put(notes.get(0), parameterNumber);
      } else if (notes.size() > 1) {
        String noteQueries = " AND (Note = ?";
        parameterNumber += 1;
        paramList.put(notes.get(0), parameterNumber);
        for (int i = 1; i < notes.size(); i++) {
          noteQueries += " AND Note = ?";
          parameterNumber += 1;
          paramList.put(notes.get(i), parameterNumber);
        }
        noteQueries += ")";
        selectReview += noteQueries;
      }
      connection = connectionManager.getConnection();
      selectReview += " LIMIT 100";
      selectStatment = connection.prepareStatement(selectReview);
      selectStatment.setInt(1, rating);
      selectStatment.setInt(2, Integer.valueOf(minPrice));
      for (Map.Entry<String, Integer> param : paramList.entrySet()) {
        if (param.getKey() == maxPrice) {
          selectStatment.setInt(param.getValue(), Integer.valueOf(param.getKey()));
        } else {
          selectStatment.setString(param.getValue(), param.getKey());
        }
      }
      System.out.println(selectStatment);
      results = selectStatment.executeQuery();
      paramList.clear();

      ReviewerDao reviewerDao = ReviewerDao.getInstance();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        Country resultCountry = new Country(results.getString("Country"));
        String description = results.getString("Description");
        Designation designation = new Designation(results.getString("Designation"));
        int points = results.getInt("Points");
        String price = results.getString("Price");
        Province province = new Province(results.getString("Province"));
        Region1 region1 = new Region1(results.getString("Region_1"));
        Region2 region2 = new Region2(results.getString("Region_2"));
        String reviewerName = results.getString("ReviewerName");
        String title = results.getString("Title");
        Variety resultVariety = new Variety(results.getString("Variety"));
        Winery winery = new Winery(results.getString("Winery"));

        Reviewer reviewer = reviewerDao.getReviewerByName(reviewerName);

        Review review = new Review(reviewId, resultCountry, description, designation,
            points, price, province, region1, region2, reviewer, title, resultVariety, winery);

        reviews.add(review);
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
    return reviews;
  }

  protected static AbstractMap.SimpleEntry<Integer, String> helper(String minPrice, String maxPrice,
      String state, String variety,
      Map<String, Integer> paramList, int parameterNumber, String selectReview) {
    if (Integer.valueOf(maxPrice) >= Integer.valueOf(minPrice)) {
      selectReview += " AND Price < ?";
      parameterNumber += 1;
      paramList.put(maxPrice, parameterNumber);
    }

    if (state != null && !state.trim().isEmpty()) {
      selectReview += " AND Province = ?";
      parameterNumber += 1;
      paramList.put(state, parameterNumber);
    }

    if (!variety.equals("all")) {
      selectReview += " AND Variety = ?";
      parameterNumber += 1;
      paramList.put(variety, parameterNumber);
    }
    AbstractMap.SimpleEntry<Integer, String> output = new AbstractMap.SimpleEntry<Integer, String>(
        parameterNumber, selectReview);
    return output;
  }
}
