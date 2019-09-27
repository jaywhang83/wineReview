package winereviews.model;

public class Review {

  protected int reviewId;
  protected Country country;
  protected String Description;
  protected Designation designation;
  protected int points;
  protected String price;
  protected Province province;
  protected Region1 region1;
  protected Region2 region2;
  protected Reviewer reviewer;
  protected String title;
  protected Variety variety;
  protected Winery winery;

  public Review(int reviewId, Country country, String description,
      Designation designation, int points, String price, Province province,
      Region1 region1, Region2 region2, Reviewer reviewer, String title,
      Variety variety, Winery winery) {
    this.reviewId = reviewId;
    this.country = country;
    Description = description;
    this.designation = designation;
    this.points = points;
    this.price = price;
    this.province = province;
    this.region1 = region1;
    this.region2 = region2;
    this.reviewer = reviewer;
    this.title = title;
    this.variety = variety;
    this.winery = winery;
  }

  public int getReviewId() {
    return reviewId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }

  public Designation getDesignation() {
    return designation;
  }

  public void setDesignation(Designation designation) {
    this.designation = designation;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  public Region1 getRegion1() {
    return region1;
  }

  public void setRegion1(Region1 region1) {
    this.region1 = region1;
  }

  public Region2 getRegion2() {
    return region2;
  }

  public void setRegion2(Region2 region2) {
    this.region2 = region2;
  }

  public Reviewer getReviewer() {
    return reviewer;
  }

  public void setReviewer(Reviewer reviewer) {
    this.reviewer = reviewer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Variety getVariety() {
    return variety;
  }

  public void setVariety(Variety variety) {
    this.variety = variety;
  }

  public Winery getWinery() {
    return winery;
  }

  public void setWinery(Winery winery) {
    this.winery = winery;
  }
}
