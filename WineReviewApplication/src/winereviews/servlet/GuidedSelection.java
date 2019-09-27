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
import winereviews.model.Review;

@WebServlet("/guidedselection")
public class GuidedSelection extends HttpServlet {

  protected ReviewDao reviewDao;

  @Override
  public void init() throws ServletException {
    reviewDao = ReviewDao.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req,
      HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html");

    req.getRequestDispatcher("/GuidedSelection.jsp").forward(req, resp);

  }
}
