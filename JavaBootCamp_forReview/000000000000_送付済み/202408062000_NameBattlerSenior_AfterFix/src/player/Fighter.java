package player;

import definer.Definer;
import player.base.Occupation;
import player.base.Player;

public class Fighter extends Player {

  /**
   * この職業のコンストラクタ
   * @param name 名前
   */
  public Fighter(String name) {
    super(name,Occupation.FIGHTER, Definer.FIGHTER_STATUS_RANGE);
  }

  @Override
  /**
   * この職業は通常攻撃しか持っていない。通常攻撃を行う
   */
  public void attack(Player opponent) {
    this.normalAttack(opponent);
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