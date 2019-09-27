package winereviews.model;

public class Province {

  protected String provinceName;

  public Province(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }
}
