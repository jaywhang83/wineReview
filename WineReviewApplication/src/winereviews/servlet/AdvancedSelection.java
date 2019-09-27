package winereviews.servlet;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

@WebServlet("/advancedselection")
public class AdvancedSelection extends HttpServlet {

  protected ReviewDao reviewDao;

  @Override
  public void init() throws ServletException {
    reviewDao = ReviewDao.getInstance();
  }

  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    List<Review> reviews = new ArrayList<>();

    String notes = req.getParameter("notes");
    String[] note_list = notes.split(" ");
    List<String> notes_all = new ArrayList<>(Arrays.asList(note_list));

    String state = req.getParameter("state");
    String price = req.getParameter("price");
    String rating_str = req.getParameter("rating");
    String variety = req.getParameter("variety");
    if (variety == null || variety.trim().isEmpty()) {
      variety = "all";
    }
    if (state == null || variety.trim().isEmpty()) {
      state = null;
    }
    if (price == null || price.trim().isEmpty()) {
      price = "0";
    }
    if (rating_str == null || rating_str.trim().isEmpty()) {
      rating_str = "0";
    }
    // if (notes== null) {
    //   messages.put("success", "Invalid Input");
    // } else {
      int rating = Integer.parseInt(rating_str);
      try {
        reviews = reviewDao
            .getReviewsByNote_Limit(state, price, "1000", rating, variety, notes_all);
        messages.put("success", "Success");
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      req.setAttribute("reviews", reviews);
    //}

    req.getRequestDispatcher("/ShowReviews.jsp").forward(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");

    request.getRequestDispatcher("/AdvancedSelection.jsp").forward(request, response);

  }
}
