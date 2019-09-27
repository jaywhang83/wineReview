package winereviews.model;

public class Reviewer {

  protected String reviewerName;
  protected String twitterHandle;

  public Reviewer(String reviewerName, String twitterHandle) {
    this.reviewerName = reviewerName;
    this.twitterHandle = twitterHandle;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  public String getTwitterHandle() {
    return twitterHandle;
  }

  public void setTwitterHandle(String twitterHandle) {
    this.twitterHandle = twitterHandle;
  }
}
