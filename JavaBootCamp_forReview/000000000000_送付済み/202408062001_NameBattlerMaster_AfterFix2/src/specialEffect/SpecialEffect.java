package specialEffect;

/** プレイヤーが受けた状態異常についてのクラス */
public class SpecialEffect {
  /** 状態異常の列挙型 */
  private SpecialEffectNum specialEffectNum;
  /** 状態異常の残りターン数 */
  private int lastTurn;

  /**
   * SpecialEffectを引数に渡し、値を格納する目的で使用する際に使用するコンストラクタ
   * 上記の目的で引数に渡すのみであればコンストラクタで値を設定する必要がない
   */
  public SpecialEffect(){
    
  }

  /**
   * 状態異常のコンストラクタ
   * @param specialEffectNum
   * @param lastTurn
   */
  public SpecialEffect(SpecialEffectNum specialEffectNum, int lastTurn) {
    this.specialEffectNum = specialEffectNum;
    this.lastTurn = lastTurn;
  }

  /**
   * 状態異常の列挙型の値を取得する
   * @return 状態異常の列挙型の値
   */
  public SpecialEffectNum getSpecialEffectNum() {
    return specialEffectNum;
  }

  /**
   * 状態異常の列挙型の値を設定する
   * ※状態異常により動けないかどうかを判定する関数（isCantMoveBySpecialEffects）にて動けないと判定された場合、
   * その関数の呼び出し元に何が原因で動けなかったのかを渡す必要がある。
   * SpecialEffectNumは列挙型のため引数に受け取った値にそのまま設定しても返せない。
   * そのためSpecialEffectクラスを上記の関数の引数に渡し、原因となる状態異常を受け取れるようにする。
   * 上記でのみ使用するセッターである。
   * @param specialEffectNum
   */
  public void setSpecialEffectNum(SpecialEffectNum specialEffectNum) {
    this.specialEffectNum = specialEffectNum;
  }

  /**
   * 状態異常の残りターン数を取得する
   * @return 状態異常の残りターン数
   */
  public int getLastTurn() {
    return lastTurn;
  }

  /**
   * 状態異常の残りターン数を設定する
   * @param lastTurn 状態異常の残りターン数
   */
  public void setLastTurn(int lastTurn) {
    this.lastTurn = lastTurn;
  }
}