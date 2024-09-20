/**
 * 部署クラス
 */
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

  public String getInformation(){
    return String.format("部署ID:%d の %s 所属", this.id , this.name);
  }
}