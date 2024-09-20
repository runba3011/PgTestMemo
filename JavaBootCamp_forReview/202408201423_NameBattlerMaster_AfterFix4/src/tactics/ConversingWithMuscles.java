package tactics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import actionNum.ActionNum;
import party.Party;
import player.base.Player;
import tactics.base.Tactics;

public class ConversingWithMuscles extends Tactics {
  /** 戦術の名前 */
  protected String name = "筋肉で語る";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ActionNum selectAction(){
    //通常攻撃のみ行う
    return ActionNum.NORMAL_ATTACK;
  }

  @Override
  public Player selectAttackedPlayer(Party party){
    //弱者を狙うのは卑怯だと筋肉が言っている…体力が一番多い敵を攻撃対象にする
    ArrayList<Player> playerList = party.getAliveMembers();
    if(playerList.size() == 0){
      System.out.println("このパーティーには生きているプレイヤーがいません");
      return null;
    }
    Player nowHpMaxPlayer = Collections.max(playerList , Comparator.comparing(Player::getNowHp));
    return nowHpMaxPlayer;
  }
}
