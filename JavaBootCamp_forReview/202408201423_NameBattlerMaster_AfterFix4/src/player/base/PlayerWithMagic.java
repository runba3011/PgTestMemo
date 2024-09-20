package player.base;

import java.util.ArrayList;
import magic.Heal;
import magic.base.Magic;

/**
 * 魔法を使えるプレイヤーの基底クラス
 */
public abstract class PlayerWithMagic extends Player {
  public static ArrayList<Magic> magicList;

  /**
   * プレイヤーのコンストラクタ
   * 
   * @param name        プレイヤーの名前
   * @param occupation  プレイヤーの職業
   * @param statusRange 各ステータスの上下限値
   */
  public PlayerWithMagic(String name, Occupation occupation, StatusRange statusRange) {
    super(name, occupation, statusRange);
  }

  /**
   * magicListのゲッター
   * ※このクラスの派生クラスでgetMagicListを実行時、magicListをそのまま使うようにするとこのクラスのmagicListが取得されてしまう。
   * この関数時実行して取得するようにすることで、各派生クラスのmagicListが使用されるようにする。
   * @return
   */
  protected abstract ArrayList<Magic> getMagicList();

  /**
   * 使用可能な魔法をすべて取得する。
   * 魔法を使用可能な職業での共通処理だが、
   * Playerクラスに作成すると魔法を使えない職業も魔法を使えるような形となってしまうため
   * 魔法を使用可能な各職業のクラスにそれぞれ同じものを作成する。
   * 
   * @return 使用可能な魔法のリスト
   */
  public ArrayList<Magic> getCanUseMagicList() {
    ArrayList<Magic> canUseMagicList = new ArrayList<>();
    for (Magic magic : getMagicList()) {
      if (magic.getMpAmount() <= this.nowMp) {
        if (magic instanceof Heal) {
          if (this.nowHp <= this.hp / 2) {
            // ヒールに限っては体力が半分以下になっていないと使わないようにする。
            canUseMagicList.add(magic);
          }
          continue;
        }

        canUseMagicList.add(magic);
      }
    }
    return canUseMagicList;
  }
}