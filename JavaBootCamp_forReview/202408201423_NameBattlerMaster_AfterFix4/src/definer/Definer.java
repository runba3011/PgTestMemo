package definer;
import java.util.ArrayList;
import java.util.Arrays;

import player.base.Player.StatusRange;
import tactics.BullyingWeak;
import tactics.ConversingWithMuscles;
import tactics.GiveSpecialEffect;
import tactics.NonViolence;
import tactics.RandomMove;
import tactics.UseMagic;
import tactics.base.Tactics;

public class Definer {

  /** 動作確認を行うときにはtrueにする */
  public final static Boolean IS_DEBUG = true;

  /** 動作確認用の各プレイヤーの名前 */
  public final static ArrayList<String> PLAYER_NAME_FOR_DEBUG = new ArrayList<>(Arrays.asList("野獣", "遠野", "五条悟", "三浦", "木村", "DAISUKE"));
  /** 動作確認用の各プレイヤーの職業 */
  // public final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("0", "0", "0", "0", "0", "0")); //戦士の動作確認
  // public final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("1", "1", "1", "1", "1", "1")); // 魔法使いの動作確認
  public final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("2", "2", "2", "2", "2", "2")); //僧侶の動作確認
  // public final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("3","3","3","3","3","3")); //五条悟の動作確認
  // public final static ArrayList<String> PLAYER_OCCUPATION_FOR_DEBUG = new ArrayList<>(Arrays.asList("0", "1", "3", "2", "0", "1")); //各職業混合

  /** 動作確認用の戦術、戦術のみ自動設定しないようにする場合はnullにすること */
  public final static String TACTICS_FOR_DEBUG = "5";
  // public final static String TACTICS_FOR_DEBUG = null;

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

  /** パーティー数の最大 */
  public final static int PARTY_COUNT_MAX = 2;

  /** 各パーティーごとのプレイヤー数 */
  public final static int PLAYER_PER_PARTY = 3;

  /** 各ターン終了時のMPの回復量 */
  public final static int MP_HEAL_AMOUNT = 5;

  /** 全ての戦術を格納するリスト */
  public final static ArrayList<Tactics> tacticsList = new ArrayList<>(Arrays.asList(
      new RandomMove(),
      new ConversingWithMuscles(),
      new UseMagic(),
      new NonViolence(),
      new GiveSpecialEffect(),
      new BullyingWeak()));

  /** 
   * 状態異常魔法を優先する戦術にて、状態異常魔法でないものが選択された場合にリトライする回数
   * 多いほど状態異常魔法が選択されやすくなる
   */
  public final static int GIVE_SPECIAL_EFFECT_MAGIC_RETRY_COUNT = 5;

  /** インスタンス化を避けるためのコンストラクタ */
  public Definer(){
    
  }
}
