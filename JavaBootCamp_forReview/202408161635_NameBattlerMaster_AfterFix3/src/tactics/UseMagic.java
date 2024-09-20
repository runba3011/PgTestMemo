package tactics;

import java.util.ArrayList;

import actionNum.ActionNum;
import party.Party;
import player.base.Player;
import tactics.base.Tactics;

public class UseMagic extends Tactics {
  /** 戦術の名前 */
  protected String name = "魔法を連発する";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ActionNum selectAction() {
    // 魔法攻撃もしくは魔法回復のみを行う
    // 上記が使用できない職業でも、魔法回復が行えない場合は魔法攻撃、魔法攻撃が行えない場合は通常攻撃を行うようになっているため問題なし
    int randomValue = random.nextInt( 1 + 1);
    switch (randomValue) {
      case 0:
        return ActionNum.MAGIC_ATTACK;
      case 1:
        return ActionNum.MAGIC_HEAL;

      default:
        System.err.println("想定外の処理が起こりました");
        return ActionNum.MAGIC_ATTACK;
    }
  }

  @Override
  public Player selectAttackedPlayer(Party party){
    ArrayList<Player> playerList = party.getAliveMembers();
    if(playerList.size() == 0){
      System.out.println("このパーティーには生きているプレイヤーがいません");
      return null;
    }
    Player selected = playerList.get(random.nextInt(playerList.size()));
    return selected;
  }
}
