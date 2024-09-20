package player;

import java.util.ArrayList;
import java.util.Arrays;

import definer.Definer;
import magic.Fire;
import magic.Thunder;
import magic.base.Magic;
import actionNum.ActionNum;
import player.base.Occupation;
import player.base.Player;
import player.base.PlayerWithMagic;
import tactics.base.Tactics;

/** 職業 魔法使い */
public class Wizard extends PlayerWithMagic {
  /** この職業のプレイヤーが使用可能な魔法 */
  public static ArrayList<Magic> magicList = new ArrayList<>(Arrays.asList(
    new Fire(),
    new Thunder()
    ));


  /**
   * この職業のコンストラクタ
   * 
   * @param name 名前
   */
  public Wizard(String name) {
    super(name,Occupation.WIZARD, Definer.WIZARD_STATUS_RANGE);
  }

  @Override
  protected ArrayList<Magic> getMagicList(){
    return magicList;
  }

  @Override
  public void attack(Player opponent , Tactics tactics) {
    ActionNum action = tactics.selectAction();
    ArrayList<Magic> magicList = getCanUseMagicList();
    if(action == ActionNum.APPEAL_FOR_PEACE){
      this.appealForPeace();
    }
    else if((action == ActionNum.MAGIC_ATTACK || action == ActionNum.SPECIAL_EFFECT_MAGIC || action == ActionNum.MAGIC_HEAL) && magicList.size() > 0){
      magicList.get(random.nextInt(magicList.size())).useMagic(this, opponent);
    }
    else{
      super.normalAttack(opponent);
    }
  }
}