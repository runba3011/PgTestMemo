package definer;
import player.base.Player.StatusRange;

public class Definer {
  /** 状態異常が発動するかを判断する際に生成するランダムな値の最大 */
  public final static int SPECIAL_EFFECT_PROBABILITY_MAX = 100;

  /** 戦士のステータスの上下限値 */
  public final static StatusRange FIGHTER_STATUS_RANGE = new StatusRange(100, 300, 0, 0, 30, 100, 30, 100, 1, 100,
      1, 50);
      
  /** 五条悟のステータスの上下限値 */
  public final static StatusRange GOJO_STATUS_RANGE = new StatusRange(500, 600, 400, 500, 300, 400, 600, 700, 0,
  0, 800, 900);

  /** 僧侶のステータスの上下限値 */
  public final static StatusRange PRIEST_STATUS_RANGE = new StatusRange(80, 200, 20, 50, 10, 70, 10, 70, 1, 100,
      20, 60);

  /** 魔法使いのステータスの上下限値 */
  public final static StatusRange WIZARD_STATUS_RANGE = new StatusRange(50, 150, 30, 80, 1, 50, 1, 50, 1, 100, 20,
      60);
  
  /** 状態異常により動けるかどうかを判断する際のランダムな値の最大 */
  public final static int CANT_MOVE_PROBABIRITY_MAX = 100;

  /** 名前より数値を生成するときの最大値 */
  public final static int GENERATE_NUMBER_MAX = 255;

  /** インスタンス化を避けるためのコンストラクタ */
  private Definer(){
    
  }
}
