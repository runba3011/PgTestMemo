package player;

import java.util.ArrayList;
import java.util.Arrays;

import actionNum.ActionNum;
import magic.Magic;
import magic.Magic.MagicNum;
import player.base.Occupation;
import player.base.Player;
import specialEffect.SpecialEffectNum;
import tactics.base.Tactics;

/** 職業 魔法使い */
public class Wizard extends Player {
  /** この職業のプレイヤーのステータスの上下限値 */
  private static StatusRange statusRange = new StatusRange(50, 150, 30, 80, 1, 50, 1, 50, 1, 100, 20,
      60);

  /** この職業のプレイヤーが使用可能な魔法 */
  public static ArrayList<Magic> magicList = new ArrayList<>(Arrays.asList(
    new Magic(MagicNum.FIRE,"ファイア",10,"敵に 10 ～ 30 の防御無視ダメージ",10,30,SpecialEffectNum.NOTHING,0,0),
    new Magic(MagicNum.THUNDER,"サンダー",20,"敵に 10 ～ 30 の防御無視ダメージ",10,30,SpecialEffectNum.NOTHING,0,0)
  ));

  /**
   * この職業のコンストラクタ
   * 
   * @param name 名前
   */
  public Wizard(String name) {
    super(name,Occupation.WIZARD, statusRange);
  }

  @Override
  public void attack(Player opponent , Tactics tactics) {
    ActionNum action = tactics.selectAction();
    ArrayList<Magic> magicList = getCanUseMagicList();
    if(action == ActionNum.APPEAL_FOR_PEACE){
      this.appealForPeace();
    }
    else if((action == ActionNum.MAGIC_ATTACK || action == ActionNum.SPECIAL_EFFECT_MAGIC || action == ActionNum.MAGIC_HEAL) && 0 < magicList.size()){
      magicList.get(random.nextInt(magicList.size())).useMagic(this, opponent);
    }
    else{
      this.normalAttack(opponent);
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