package player.base;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;

import definer.Definer;
import specialEffect.SpecialEffect;
import specialEffect.SpecialEffectNum;
import tactics.base.Tactics;

/**
 * プレイヤーの基底クラス
 */
public abstract class Player {
  /** プレイヤー名 */
  protected String name;
  /** プレイヤーの職業 */
  protected Occupation occupation;
  /** プレイヤーの最大HP */
  protected int hp;
  /** プレイヤーの現在のHP */
  protected int nowHp;
  /** プレイヤーの最大MP */
  protected int mp;
  /** プレイヤーの現在のMP */
  protected int nowMp;
  /** プレイヤーの攻撃力 */
  protected int str;
  /** プレイヤーの防御力 */
  protected int def;
  /** プレイヤーの運 */
  protected int luck;
  /** プレイヤーの素早さ */
  protected int agi;
  /** プレイヤーの状態異常 */
  protected ArrayList<SpecialEffect> specialEffectList = new ArrayList<>();

  protected static Random random = new Random();

  /** 名前より各能力値を取得するとき、ハッシュコードの何番目を取得するかに付いて決めるための列挙型 */
  public static enum GenerateNumberIndex {
    HP,
    MP,
    STR,
    DEF,
    LUCK,
    AGI,
  }

  /** 各能力の最小及び最大を格納するためのクラス */
  public static class StatusRange {
    protected int minHp;
    protected int maxHp;
    protected int minMp;
    protected int maxMp;
    protected int minStr;
    protected int maxStr;
    protected int minDef;
    protected int maxDef;
    protected int minLuck;
    protected int maxLuck;
    protected int minAgi;
    protected int maxAgi;

    public StatusRange(int minHp, int maxHp, int minMp, int maxMp, int minStr,
        int maxStr,
        int minDef, int maxDef, int minLuck, int maxLuck, int minAgi, int maxAgi) {
      this.minHp = minHp;
      this.maxHp = maxHp;
      this.minMp = minMp;
      this.maxMp = maxMp;
      this.minStr = minStr;
      this.maxStr = maxStr;
      this.minDef = minDef;
      this.maxDef = maxDef;
      this.minLuck = minLuck;
      this.maxLuck = maxLuck;
      this.minAgi = minAgi;
      this.maxAgi = maxAgi;
    }

    public int getMinHp() {
      return minHp;
    }

    public int getMaxHp() {
      return maxHp;
    }

    public int getMinMp() {
      return minMp;
    }

    public int getMaxMp() {
      return maxMp;
    }

    public int getMinStr() {
      return minStr;
    }

    public int getMaxStr() {
      return maxStr;
    }

    public int getMinDef() {
      return minDef;
    }

    public int getMaxDef() {
      return maxDef;
    }

    public int getMinLuck() {
      return minLuck;
    }

    public int getMaxLuck() {
      return maxLuck;
    }

    public int getMinAgi() {
      return minAgi;
    }

    public int getMaxAgi() {
      return maxAgi;
    }
  }

  /**
   * プレイヤーのコンストラクタ
   * @param name プレイヤーの名前
   * @param occupation プレイヤーの職業
   * @param statusRange 各ステータスの上下限値
   */
  public Player(String name, Occupation occupation, StatusRange statusRange) {
    this.occupation = occupation;

    int hp = getStatus(name, GenerateNumberIndex.HP, statusRange);
    int mp = getStatus(name, GenerateNumberIndex.MP, statusRange);
    int str = getStatus(name, GenerateNumberIndex.STR, statusRange);
    int def = getStatus(name, GenerateNumberIndex.DEF, statusRange);
    int luck = getStatus(name, GenerateNumberIndex.LUCK, statusRange);
    int agi = getStatus(name, GenerateNumberIndex.AGI, statusRange);

    this.name = name;
    this.hp = hp;
    this.nowHp = this.hp;
    this.mp = mp;
    this.nowMp = this.mp;
    this.str = str;
    this.def = def;
    this.luck = luck;
    this.agi = agi;
  }

  /**
   * プレイヤー名を取得
   * 
   * @return プレイヤー名
   */
  public String getName() {
    return name;
  }

  /**
   * 職業を取得
   * @return 職業
   */
  public Occupation getOccupation() {
    return occupation;
  }

  /**
   * 最大HPを取得
   * @return 最大HPを返す
   */
  public int getHp() {
    return hp;
  }

  /**
   * 現在のHPを取得
   * @return 現在のHPを返す
   */
  public int getNowHp() {
    return nowHp;
  }

  /**
   * 現在のHPを設定する、ただし0及び最大HPより大きくならないようにする
   * @param nowHp 設定する現在のHP
   */
  public void setNowHp(int nowHp) {
    if(nowHp > this.hp){
      this.nowHp = this.hp;
    }
    else if(nowHp <= 0){
      this.nowHp = 0;
    }
    else{
      this.nowHp = nowHp;
    }
  }

  /**
   * MPを取得
   * @return MPを返す
   */
  public int getMp() {
    return mp;
  }

  /**
   * 現在のMPを設定する
   * @param nowHp 設定する現在のMP
   */
  public int getNowMp() {
    return nowMp;
  }

  public void setNowMp(int nowMp) {
    if(nowMp > this.mp){
      this.nowMp = this.mp;
    }
    else if(nowMp <= 0){
      this.nowMp = 0;
    }
    else{
      this.nowMp = nowMp;
    }
  }

  /**
   * 攻撃力を取得
   * @return 攻撃力を返す
   */
  public int getStr() {
    return str;
  }

  /**
   * 防御力を取得
   * @return 防御力を返す
   */
  public int getDef() {
    return def;
  }

  /**
   * 運を取得
   * @return 運を返す
   */
  public int getLuck() {
    return luck;
  }

  /**
   * 素早さを取得
   * @return 素早さを返す
   */
  public int getAgi() {
    return agi;
  }

  /**
   * このプレイヤーの状態異常を取得する
   * @return このプレイヤーの状態異常を返す
   */
  public ArrayList<SpecialEffect> getSpecialEffectList() {
    return specialEffectList;
  }

  /**
   * いずれかの状態異常により動けないかを取得する
   * 
   * @param cantMoveSpecialEffect 動けない理由となる状態異常を設定する（このチェック処理の後、動けない旨のメッセージを別の関数で表示するため）
   * @return 動けない場合trueを返す
   */
  public Boolean isCantMoveBySpecialEffects(SpecialEffect cantMoveSpecialEffect){
    for(SpecialEffect effect : this.specialEffectList){
      SpecialEffectNum effectNum = effect.getSpecialEffectNum();
      if(effectNum.isCantMove()){
        cantMoveSpecialEffect.setSpecialEffectNum(effectNum);
        return true;
      }
    }

    return false;
  }

  /**
   * 対戦相手を攻撃する
   * @param opponent 攻撃対象のプレイヤー
   */
  public abstract void attack(Player opponent , Tactics tactics);

  /**
   * 通常攻撃を行う
   * @param opponent 攻撃対象のプレイヤー
   */
  protected void normalAttack(Player opponent) {
    if(Definer.IS_DEBUG){
      System.out.printf("%s(素早さ%d)の攻撃！\n", this.name , this.agi);
    }
    else{
      System.out.printf("%sの攻撃！\n", this.name);
    }
    boolean isCritical = isCritical();
    int damage = !isCritical ? this.str - opponent.getDef() : this.str;
    if (isCritical)
      System.out.print("会心の一撃！");
    if (damage > 0) {
      System.out.printf("%sに %d のダメージ！\n", opponent.getName(), damage);
      opponent.setNowHp(opponent.getNowHp() - damage);
    } else {
      System.out.println(String.format("%sへの攻撃がミス！", opponent.getName()));
    }
  }


  /**
   * 名前をもとにステータスを取得する
   * @param name  キャラクターの名前
   * @param index 取得するステータスのインデックス(定数を使用すること)
   * @return ステータスの値
   */
  public int getStatus(String name, GenerateNumberIndex index, StatusRange statusRange) {
    int min;
    int max;

    switch (index) {
      case HP:
        min = statusRange.getMinHp();
        max = statusRange.getMaxHp();
        break;
      case MP:
        min = statusRange.getMinMp();
        max = statusRange.getMaxMp();
        break;
      case STR:
        min = statusRange.getMinStr();
        max = statusRange.getMaxStr();
        break;
      case DEF:
        min = statusRange.getMinDef();
        max = statusRange.getMaxDef();
        break;
      case LUCK:
        min = statusRange.getMinLuck();
        max = statusRange.getMaxLuck();
        break;
      case AGI:
        min = statusRange.getMinAgi();
        max = statusRange.getMaxAgi();
        break;
      default:
        System.out.println("想定外の処理が行われました");
        min = 1;
        max = 1;
        break;
    }

    int randomRangeMax = max - min;
    // 名前をもとに生成した数値が最大値の何%（以下、割合 と呼称する）かを算出し、それをランダムな値の最大値にかけ、それを最小値に足すことで能力の値を
    // 特定の範囲のランダムに収めるようにすることを実現している。
    // 下記で使用している変数はすべて整数のため型を変換しないと上記が実現できない。
    // 小数点以下も扱える型を割合を算出する際に使用する値の一部に使用することで実現できるようにしている。
    int randomValue = (int) (randomRangeMax
        * ((double) generateNumber(name, index.ordinal()) / Definer.GENERATE_NUMBER_MAX));
    int result = min + randomValue;
    return result;
  }

  /**
   * ハッシュダイジェストから数値を取り出す
   * @param name  名前
   * @param index 何番目の数値を取り出すか
   * @return 数値(0 ～ 255)
   */
  public static int generateNumber(String name, int index) {
    try {
      String digest = getHashDigest(name);
      String hex = digest.substring(
          index * 2, index * 2 + 2);

      return Integer.parseInt(hex, 16);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 名前をもとにハッシュ値を取得する
   * 
   * @param name 名前
   * @return ハッシュ値
   */
  public static String getHashDigest(String name) {
    try {
      // ハッシュ値を取得する
      byte[] result = MessageDigest.getInstance("SHA-1")
          .digest(name.getBytes());
      return String.format(
          "%040x",
          new BigInteger(1, result));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 平和を訴える（つまり何もしない）
   * 全職業に共通の処理のため基底クラスに宣言した
   */
  protected void appealForPeace(){
    System.out.println(String.format("%s は 平和を訴えている！", this.name));
  }

  /**
   * クリティカルが発生したかを取得する
   * @return クリティカルが発生したか
   */
  protected boolean isCritical() {
    return random.nextInt(Definer.GENERATE_NUMBER_MAX) <= this.luck;
  }

  /**
   * 生きているかを取得する
   * @return 生きているか
   */
  public boolean isDead() {
    return nowHp <= 0;
  }

  /**
   * 状態異常によるダメージを受ける。ただし、状態異常では必ずHPは1より小さくならないようにする。
   */
  public void receiveSpecialEffectDamage() {
    for (SpecialEffect effect : this.specialEffectList) {
      SpecialEffectNum effectNum = effect.getSpecialEffectNum();
      int damage = effectNum.getDamage();
      damage = this.nowHp - damage <= 0 ? this.nowHp - 1 : damage;
      if(damage <= 0){
        continue;
      }
      this.setNowHp(this.getNowHp() - damage);
      System.out.println(String.format("%s は %s により %d のダメージを受けた！", this.name, effectNum.toString(), damage));
    }
  }
}