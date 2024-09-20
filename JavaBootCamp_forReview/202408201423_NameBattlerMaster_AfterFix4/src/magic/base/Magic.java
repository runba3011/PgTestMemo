package magic.base;

import java.util.Random;
import player.base.Player;

/** 魔法についての基底クラス */
public class Magic {
  protected static Random random = new Random();
  /** 魔法の名前 */
  protected String name;
  /** 使用するのに必要なMPの量 */
  protected int mpAmount;
  /**
   * 魔法についての説明
   * ※現状使用していないが今後魔法を選択したり、詳細を表示したりできるようにする機能を追加する場合に備え残しておく。
   */
  protected String description;
  /** 魔法による最小ダメージ */
  protected int minDamage;
  /** 魔法による最大ダメージ */
  protected int maxDamage;

  /**
   * 魔法のコンストラクタ
   * @param magicNum 魔法の番号
   * @param name 名前
   * @param mpAmount 必要なMP
   * @param description 説明
   * @param minDamage 最小ダメージ
   * @param maxDamage 最大ダメージ
   */
  public Magic(String name, int mpAmount, String description, int minDamage,
      int maxDamage) {
    this.name = name;
    this.mpAmount = mpAmount;
    this.description = description;
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
  }

  public String getName() {
    return name;
  }
  public int getMpAmount() {
    return mpAmount;
  }
  public String getDescription() {
    return description;
  }
  public int getDamage(){
    int randomRange = this.maxDamage - this.minDamage;
    return this.minDamage + random.nextInt(randomRange + 1);
  }

  /**
   * 魔法を使う
   * @param user 魔法を使うプレイヤー
   * @param target 魔法を使われるプレイヤー
   */
  public void useMagic(Player user , Player target){
    System.out.println(String.format("%s は %s を使った！" , user.getName() , this.name));
    user.setNowMp(user.getNowMp() - this.mpAmount);
    
    giveDamage(target);
  }

  /**
   * 魔法により対象にダメージを与える
   * @param target
   */
  protected void giveDamage(Player target){
    int damage = getDamage();
    System.out.println(String.format("%s に%dのダメージ！", target.getName(), Math.abs(damage)));
    target.setNowHp(target.getNowHp() - damage);
  }
}