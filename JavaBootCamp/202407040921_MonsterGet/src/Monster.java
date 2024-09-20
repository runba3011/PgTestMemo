import java.util.Random;

public class Monster {
  private static Random random = new Random();
  public final static int CAPTURE_RATE_MAX = 100;
  private String name; // 名前
  private int hpValue; // HP
  private int powerValue; // 攻撃
  private int defenseValue; // 防御
  private int encountRate; // 発生率
  private int captureRate; // 捕獲率

  public String getName() {
    return name;
  }

  public int getHpValue() {
    return hpValue;
  }

  public int getPowerValue() {
    return powerValue;
  }

  public int getDefenseValue() {
    return defenseValue;
  }

  public int getEncountRate() {
    return encountRate;
  }

  public int getCaptureRate() {
    return captureRate;
  }

  public Monster(
      String name,
      int hpValue,
      int powerValue,
      int defenseValue,
      int encountRate,
      int captureRate) {
    this.name = name;
    this.hpValue = hpValue;
    this.powerValue = powerValue;
    this.defenseValue = defenseValue;
    this.encountRate = encountRate;
    this.captureRate = captureRate;
  }

  public void showInformation() {
    System.out.println("モンスターの情報：");
    System.out.println(String.format("名前：%s", this.name));
    System.out.println(String.format("HP：%d", this.hpValue));
    System.out.println(String.format("攻撃力：%d", this.powerValue));
    System.out.println(String.format("防御力：%d", this.defenseValue));
  }

  /**
   * 捕獲ポイントを取得（式：(HP+攻撃+防御)*10）
   * 
   * @return 捕獲ポイント
   */
  public int calcCapturedPoint() {
    return 0;
  }

  /**
   * 捕獲できたかどうかの判定を行う
   * 
   * @param correctValue 捕獲率の補正値
   * @return true: 捕獲成功 / false: 捕獲失敗
   */
  public boolean canCapture(int correctValue) {
    int randomValue = random.nextInt(CAPTURE_RATE_MAX);
    return randomValue <= this.captureRate + correctValue;
  }
}