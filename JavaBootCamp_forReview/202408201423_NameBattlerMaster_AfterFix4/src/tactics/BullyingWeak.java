package tactics;

import java.util.ArrayList;
import java.util.Comparator;

import actionNum.ActionNum;
import party.Party;
import player.base.Player;
import tactics.base.Tactics;

public class BullyingWeak extends Tactics {
  /** 戦術の名前 */
  protected String name = "弱い者いじめ";

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

    //体力が一番低い相手を狙う
    //partyListから取得したListを並び替えると、partyList側にも影響が出てしまう。
    //そのためplayerList内の要素をコピーしたリストを作成し、それを並び替える。
    ArrayList<Player> playerListCopy = new ArrayList<Player>(playerList);
    playerListCopy.sort(Comparator.comparingInt(Player::getNowHp));
    Player selected = playerListCopy.get(0);
    return selected;
  }
}
