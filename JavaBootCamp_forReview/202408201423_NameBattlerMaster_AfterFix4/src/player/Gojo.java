package player;
import java.util.ArrayList;
import java.util.Arrays;

import definer.Definer;
import magic.LimitLessAka;
import magic.LimitLessAo;
import magic.LimitLessMurasaki;
import magic.LimitLessMuryokusyo;
import magic.base.Magic;

import java.util.Optional;
import actionNum.ActionNum;
import player.base.Occupation;
import player.base.Player;
import player.base.PlayerWithMagic;
import tactics.base.Tactics;

/** 職業 五条悟 */
public class Gojo extends PlayerWithMagic {

  /** この職業のプレイヤーが使用可能な魔法 */
  public static ArrayList<Magic> magicList = new ArrayList<>(Arrays.asList(
    new LimitLessAka(),
    new LimitLessAo(),
    new LimitLessMurasaki(),
    new LimitLessMuryokusyo()
    ));

  /**
   * この職業のコンストラクタ
   * 
   * @param name 名前
   */
  public Gojo(String name) {
    super(name,Occupation.GOJO, Definer.GOJO_STATUS_RANGE);
  }

  @Override
  protected ArrayList<Magic> getMagicList(){
    return magicList;
  }

  @Override
  public void attack(Player opponent , Tactics tactics) {
    ActionNum action = tactics.selectAction();
    ArrayList<Magic> magicList = getCanUseMagicList();
    Optional<Magic> firstMuryoKusyoMagic = magicList.stream().filter(magic -> magic instanceof LimitLessMuryokusyo).findFirst();
    Magic muryoKusyoMagic = firstMuryoKusyoMagic.isPresent() ? firstMuryoKusyoMagic.get() : null;
    if(action == ActionNum.APPEAL_FOR_PEACE){
      this.appealForPeace();
    }
    else if(action == ActionNum.SPECIAL_EFFECT_MAGIC && muryoKusyoMagic != null){
      muryoKusyoMagic.useMagic(this, opponent);
    }
    else if((action == ActionNum.MAGIC_ATTACK || action == ActionNum.MAGIC_HEAL) && magicList.size() > 0){
      magicList.get(random.nextInt(magicList.size())).useMagic(this, opponent);
    }
    else{
      super.normalAttack(opponent);
    }
  }
}