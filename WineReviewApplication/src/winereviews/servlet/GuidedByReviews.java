package winereviews.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import winereviews.dal.ReviewDao;
import winereviews.model.Review;

@WebServlet("/guidedbyreviews")
public class GuidedByReviews extends HttpServlet {

  protected ReviewDao reviewDao;

  @Override
  public void init() throws ServletException {
    reviewDao = ReviewDao.getInstance();
  }

  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String[]> names = req.getParameterMap();
    List<Review> reviews = reviewDao.getRecommendations(names);

    req.setAttribute("reviews", reviews);
    req.getRequestDispatcher("/ShowReviews.jsp").forward(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html");

    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    List<Review> reviews = new ArrayList<>();

    try {
      reviews = reviewDao.getGuidedReviews();
      messages.put("success", "Success");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    req.setAttribute("reviews", reviews);

    req.getRequestDispatcher("/GuidedByReviews.jsp").forward(req, resp);

  }
}
