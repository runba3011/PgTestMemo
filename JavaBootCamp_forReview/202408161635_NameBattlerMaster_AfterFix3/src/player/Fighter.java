package player;

import definer.Definer;
import actionNum.ActionNum;
import player.base.Occupation;
import player.base.Player;
import tactics.base.Tactics;

public class Fighter extends Player {

  /**
   * この職業のコンストラクタ
   * @param name 名前
   */
  public Fighter(String name) {
    super(name,Occupation.FIGHTER, Definer.FIGHTER_STATUS_RANGE);
  }

  @Override
  public void attack(Player opponent , Tactics tactics) {
    ActionNum action = tactics.selectAction();
    if(action == ActionNum.APPEAL_FOR_PEACE){
      this.appealForPeace();
    }
    else{
      //この職業は 平和を訴える 通常攻撃 の2パターンしかない。
      //平和を訴えない場合は通常攻撃として扱う。
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
}