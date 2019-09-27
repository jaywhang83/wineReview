package winereviews.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import winereviews.dal.ReviewDao;
import winereviews.dal.TastingNotesDao;
import winereviews.model.Review;
import winereviews.model.TastingNotes;

@WebServlet("/guidedbynotes")
public class GuidedByNotes extends HttpServlet {

  protected ReviewDao reviewDao;
  protected TastingNotesDao tastingNotesDao;

  @Override
  public void init() throws ServletException {
    reviewDao = ReviewDao.getInstance();
    tastingNotesDao = TastingNotesDao.getInstance();
  }
  protected void doPost(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String[]> notes_map = req.getParameterMap();

    List<String> notes = new ArrayList<>();

    List<Review> reviews = new ArrayList<>();
    HashMap<Review, Integer> m = new HashMap<>();
    try {
      for (Map.Entry<String, String[]> entry : notes_map.entrySet()) {
        System.out.println(entry.getValue());
        notes.add(entry.getValue()[0]);
        reviews.addAll(reviewDao.getReviewsByNote_Limit(null, "0", "1000", 95, "all", notes));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.setAttribute("reviews", reviews);
    req.getRequestDispatcher("/ShowReviews.jsp").forward(req, resp);
  }

  protected void doGet(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {

    Map<String, String[]> names = req.getParameterMap();
    List<TastingNotes> tastingNotes = null;
    try {
      tastingNotes = tastingNotesDao.getNotes();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    req.setAttribute("tastingNotes", tastingNotes);
    req.getRequestDispatcher("/GuidedByNotes.jsp").forward(req, resp);
  }
}
