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
      super.normalAttack(opponent);
    }
  }
}