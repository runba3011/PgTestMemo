package tactics;

import java.util.ArrayList;

import actionNum.ActionNum;
import party.Party;
import player.base.Player;
import tactics.base.Tactics;

public class NonViolence extends Tactics {
  /** 戦術の名前 */
  protected String name = "非暴力・不服従";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ActionNum selectAction(){
    //魔法攻撃のみを行う
    //魔法攻撃が使用できない職業でも、下記の値が返された場合は通常攻撃を行うようになっているため問題なし
    return ActionNum.APPEAL_FOR_PEACE;
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
