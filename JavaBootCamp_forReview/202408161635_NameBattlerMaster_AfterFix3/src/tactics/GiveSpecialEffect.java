package tactics;

import java.util.ArrayList;
import java.util.stream.Collectors;

import actionNum.ActionNum;
import party.Party;
import player.base.Player;
import tactics.base.Tactics;

public class GiveSpecialEffect extends Tactics {
  /** 戦術の名前 */
  protected String name = "状態異常魔法優先";
  /** 状態異常魔法でないものが選択された場合にリトライする回数、多いほど状態異常魔法が選択されやすくなる */
  private final int GIVE_SPECIAL_EFFECT_MAGIC_RETRY_COUNT = 5;

  @Override
  public String getName() {
    return name;
  }

  @Override
  public ActionNum selectAction(){
    int actionInt = random.nextInt(ActionNum.values().length);
    ActionNum action = ActionNum.convertintToActionNum(actionInt);
    int loopCount = 0;

    //状態異常魔法が選択されなかった場合、
    //特定回数になるまで選びなおすことで状態異常魔法が選択されやすくする
    while (action != ActionNum.SPECIAL_EFFECT_MAGIC && loopCount < GIVE_SPECIAL_EFFECT_MAGIC_RETRY_COUNT) {
      actionInt = random.nextInt(ActionNum.values().length);
      action = ActionNum.convertintToActionNum(actionInt);
      loopCount ++;
    }

    return action;
  }

  @Override
  public Player selectAttackedPlayer(Party party){
    ArrayList<Player> playerList = party.getAliveMembers();
    if(playerList.size() == 0){
      System.out.println("このパーティーには生きているプレイヤーがいません");
      return null;
    }

    //状態異常を受けていないプレイヤーを狙う
    Player selected = null;
    ArrayList<Player> noSpecialEffectPlayerList = new ArrayList<>(playerList.stream().filter(player -> player.getSpecialEffectList().size() == 0).collect(Collectors.toList()));
    if(0 < noSpecialEffectPlayerList.size()){
      selected = noSpecialEffectPlayerList.get(random.nextInt(noSpecialEffectPlayerList.size()));
      return selected;
    }

    //状態異常を受けていないプレイヤーがいなければランダムで狙う
    selected = playerList.get(random.nextInt(playerList.size()));
    return selected;
  }
}
