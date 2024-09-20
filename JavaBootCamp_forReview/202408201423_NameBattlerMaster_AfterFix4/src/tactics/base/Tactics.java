package tactics.base;

import java.util.Random;

import actionNum.ActionNum;
import party.Party;
import player.base.Player;

public abstract class Tactics{
  protected static Random random = new Random();
  /** 戦術の名前 */
  protected static String name = "";

  /** 名前を取得する */
  public abstract String getName();

  /** 行動を選択する */
  public abstract ActionNum selectAction();

  /**
   * 攻撃対象のプレイヤーを選択する
   * @param attackedParty 攻撃されるパーティー
   * @return 攻撃対象のプレイヤー
   */
  public abstract Player selectAttackedPlayer(Party attackedParty);
}