package specialEffect;
import java.util.Random;

import definer.Definer;

/** 状態異常の列挙型 */
public enum SpecialEffectNum{
  NOTHING("なし",0,"-",0,0),
  PARALYSIS("麻痺",20,"%s は体がしびれて動けない！",0,0),
  POISON("毒",5,"%s はおなかを下している！",10,30),
  CRIPPLE("廃人化",100,"%s の脳は情報を処理しきれていない！",0,0),
  AUTO_HEAL("自動回復",0,"-",-10,-50),;
  
  private static Random random = new Random();

  /** 状態異常の名前 */
  private String name;
  /** 状態異常により動けなくなる確率 */
  private int cantMoveProbability;
  /** 状態異常により動けない場合に表示するメッセージ */
  private String cantMoveMessage;
  /** 状態異常により自動的に受ける最小ダメージ */
  private int minDamage;
  /** 状態異常により自動的に受ける最大ダメージ */
  private int maxDamage;

  /**
   * 状態異常のコンストラクタ
   * @param name 名前
   * @param cantMoveProbability 状態異常により動けなくなる確率
   * @param cantMoveMessage 状態異常により動けない場合に表示するメッセージ
   * @param minDamage 状態異常により自動的に受ける最小ダメージ
   * @param maxDamage 状態異常により自動的に受ける最大ダメージ
   */
  private SpecialEffectNum(String name, int cantMoveProbability, String cantMoveMessage, int minDamage,
      int maxDamage) {
    this.name = name;
    this.cantMoveProbability = cantMoveProbability;
    this.cantMoveMessage =cantMoveMessage;
    this.minDamage =minDamage;
    this.maxDamage = maxDamage;
  }

  /**
   * 状態異常を文字列に変換する
   */
  @Override
  public String toString(){
    return name;
  }

  /**
   * 動けないかどうかを取得する
   * @return 動けない場合はtrueを返す
   */
  public Boolean isCantMove(){
    int randomValue = random.nextInt(Definer.CANT_MOVE_PROBABIRITY_MAX);
    return randomValue < this.cantMoveProbability;
  }

  /**
   * 動けない場合のメッセージを取得する
   * @return 動けない場合のメッセージ
   */
  public String getCantMoveMessage() {
    return cantMoveMessage;
  }

  /**
   * この状態異常によるダメージを取得する
   * @return この状態異常によるダメージ
   */
  public int getDamage() {
    //ダメージ無し を表現するために、最小、最大ともに0に設定している場合がある。
    //その場合、random.nextIntで最小と最大が同じのためエラーが発生してしまう。
    //発生しないよう、最小と最大が同じならば最小側の値を返す。
    if(minDamage == maxDamage){
      return minDamage;
    }
    else if(minDamage < maxDamage){
      return random.nextInt(maxDamage - minDamage + 1) + minDamage;
    }else{
      //回復する状態異常を実現するために、ダメージ量を負にしているものがある。
      //その場合、最小の回復量を-10、最大の回復量を-30というようになっており、実際の値は最小の方が大きい。
      //上記をnextIntに使用すると最大値の方が最小値より小さいためエラーが発生する。
      //対策のため、最大の方が小さければ最小、最大を反転する。
      return random.nextInt(minDamage - maxDamage + 1) + maxDamage;
    }
  }
}
