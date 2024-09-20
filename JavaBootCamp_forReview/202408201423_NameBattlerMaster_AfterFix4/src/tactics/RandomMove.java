package tactics;

import java.util.ArrayList;

import actionNum.ActionNum;
import party.Party;
import player.base.Player;
import tactics.base.Tactics;

public class RandomMove extends Tactics {
  /** 戦術の名前 */
  protected String name = "ランダム行動";

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ActionNum selectAction(){
    int actionInt = random.nextInt(ActionNum.values().length);
    return ActionNum.convertIntToActionNum(actionInt);
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
