package player.base;

/**
 * 各職業についての列挙型
 */
public enum Occupation {
  FIGHTER("戦士"),
  WIZARD("魔法使い"),
  PRIEST("僧侶"),
  GOJO("五条悟");

  private String name;

  /**
   * 職業のコンストラクタ
   * @param name 名前
   */
  Occupation(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}