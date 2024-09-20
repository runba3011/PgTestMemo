package player;

import java.util.ArrayList;
import java.util.Arrays;

import definer.Definer;
import magic.Magic;
import magic.Magic.MagicNum;
import player.base.Occupation;
import player.base.Player;
import specialEffect.SpecialEffectNum;

/** 職業 魔法使い */
public class Wizard extends Player {

  /** この職業のプレイヤーが使用可能な魔法 */
  public static ArrayList<Magic> magicList = new ArrayList<>(Arrays.asList(
      new Magic(MagicNum.FIRE, "ファイア", 10, "敵に 10 ～ 30 の防御無視ダメージ", 10, 30, SpecialEffectNum.NOTHING, 0, 0),
      new Magic(MagicNum.THUNDER, "サンダー", 20, "敵に 10 ～ 30 の防御無視ダメージ", 10, 30, SpecialEffectNum.NOTHING, 0, 0)
      ));

  /**
   * この職業のコンストラクタ
   * 
   * @param name 名前
   */
  public Wizard(String name) {
    super(name,Occupation.WIZARD, Definer.WIZARD_STATUS_RANGE);
  }

  @Override
  public void attack(Player opponent) {
    ArrayList<Magic> magicList = getCanUseMagicList();
    // 過去の指摘にて「正常系の処理を先に記載すること」とあったため、使用可能な魔法のリストが空の場合 を優先し、空でない場合 という条件は使用しなかった。
    // ただし、この職業は魔法を使うことが優先され、通常攻撃を行うのは苦肉の策である。
    // この場合でも、先にリストが空の場合（魔法を使えない場合）の処理を先に書くのがよいか？
    if (magicList.size() == 0) {
      normalAttack(opponent);
    } else {
      magicList.get(random.nextInt(magicList.size())).useMagic(this, opponent);
    }
  }

  /**
   * 通常攻撃を行う
   * 通常攻撃は各職業で共通処理だが、
   * attack関数はオーバーライドして作成する必要があるため、基底クラス側では作成しない。
   * コピペして作成しやすいよう、別関数に分離した。
   * 
   * @param opponent 攻撃対象のプレイヤー
   */
  private void normalAttack(Player opponent) {
    System.out.printf("%sの攻撃！\n", this.name);
    boolean isCritical = isCritical();
    int damage = !isCritical ? this.str - opponent.getDef() : this.str;
    if (isCritical)
      System.out.print("会心の一撃！");
    if (0 < damage) {
      System.out.printf("%sに %d のダメージ！\n", opponent.getName(), damage);
      opponent.setNowHp(opponent.getNowHp() - damage);
    } else {
      System.out.println("攻撃がミス！");
    }
  }

  /**
   * 使用可能な魔法をすべて取得する。
   * 魔法を使用可能な職業での共通処理だが、
   * Playerクラスに作成すると魔法を使えない職業も魔法を使えるような形となってしまうため
   * 魔法を使用可能な各職業のクラスにそれぞれ同じものを作成する。
   * 
   * @return 使用可能な魔法のリスト
   */
  protected ArrayList<Magic> getCanUseMagicList() {
    ArrayList<Magic> canUseMagicList = new ArrayList<>();
    for (Magic magic : magicList) {
      if (magic.getMpAmount() <= this.nowMp) {
        // この職業はヒールを使えないためこの処理は不要
        // if (magic.getMagicNum() == Magic.MagicNum.HEAL) {
        // if (this.nowHp <= this.hp / 2) {
        // // ヒールに限っては体力が半分以下になっていないと使わないようにする。
        // canUseMagicList.add(magic);
        // }
        // continue;
        // }

        canUseMagicList.add(magic);
      }
    }
    return canUseMagicList;
  }
}