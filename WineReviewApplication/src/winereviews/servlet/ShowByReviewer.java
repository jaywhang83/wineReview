package winereviews.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import winereviews.dal.ReviewerDao;
import winereviews.model.Review;
import winereviews.model.Reviewer;

@WebServlet("/showbyreviewer")
public class ShowByReviewer extends HttpServlet {

  protected ReviewerDao reviewerDao;
  protected ReviewDao reviewDao;

  @Override
  public void init() throws ServletException {
    reviewerDao = ReviewerDao.getInstance();
    reviewDao = ReviewDao.getInstance();
  }

  protected void doGet(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    String twitterHandle = req.getParameter("reviewer");
    System.out.println(twitterHandle);
    Reviewer reviewer = null;
    List<Review> reviews = null;
    try {
      reviewer = reviewerDao.getReviewerByTwitterHandle(twitterHandle);
      reviews = reviewDao.getReviewsByReviewer(reviewer);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.setAttribute("reviews", reviews);
    req.getRequestDispatcher("/ShowReviews.jsp").forward(req, resp);
  }

}
