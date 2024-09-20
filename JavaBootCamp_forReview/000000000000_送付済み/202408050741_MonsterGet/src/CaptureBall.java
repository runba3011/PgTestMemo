public class CaptureBall {

  private String name; // 名称
  private int correctValue; // 捕獲率の補正値
  private int count; // 所持数

  /**
   * 捕獲玉のコンストラクタ
   * @param name 名前
   * @param correctValue 捕獲率の補正値
   * @param count 所持数
   */
  CaptureBall(String name, int correctValue, int count) {
    this.name = name;
    this.correctValue = correctValue;
    this.count = count;
  }

  public String getName() {
    return name;
  }

  public int getCorrectValue() {
    return correctValue;
  }

  public int getCount() {
    return count;
  }

  /**
   * 捕獲玉を使用する
   */
  public void use() {
    this.count--;
  }
}