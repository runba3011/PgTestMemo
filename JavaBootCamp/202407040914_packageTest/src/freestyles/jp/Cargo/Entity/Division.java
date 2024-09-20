package freestyles.jp.Cargo.Entity;
public class Division {

  private int id;
  private String name;

  public Division id(int id) {
    this.id = id;
    return this;
  }

  public Division name(String name) {
    this.name = name;
    return this;
  }

  @Override
  public String toString() {

    return String.format(
        "部署ID:%d の %s",
        this.id,
        this.name);
  }
}