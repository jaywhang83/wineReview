package winereviews.tools;

import java.util.List;
import winereviews.model.*;
import winereviews.dal.*;

import java.sql.SQLException;

public class Inserter {

  public static void main(String[] args) throws SQLException {
    Region1Dao regionDao = Region1Dao.getInstance();

    List<Region1> regions = regionDao.getRegions();

    for (Region1 r : regions) {
      System.out.println(r.getRegion());
    }


  }

}
