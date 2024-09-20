/** 
  部署クラス
  部署の情報を格納するクラス
  @author Minoru Takano
  @version 1.0
*/
public class Division {

  /** 部署ID */
  private int id;
  /** 部署名 */
  private String name;

  /**
   * 部署IDを設定して自身を返す
   * @param id 部署ID
   * @return 自分自身のインスタンス
   */
  public Division id(int id) {
    this.id = id;
    return this;
  }

  /**
   * 部署名を設定して自身を返す
   * @param name 部署名
   * @return 自分自身のインスタンス
   */
  public Division name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 部署IDと部署名を返す
   * @return 部署IDと部署名を結合した文字列
   */
  @Override
  public String toString() {

    return String.format(
        "部署ID:%d の %s",
        this.id,
        this.name);
  }
}