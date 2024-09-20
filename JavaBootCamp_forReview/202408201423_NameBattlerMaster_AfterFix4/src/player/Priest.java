package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import definer.Definer;
import magic.Heal;
import magic.Parryze;
import magic.Poison;
import magic.base.Magic;
import magic.base.MagicWithSpecialEffect;
import actionNum.ActionNum;
import player.base.Occupation;
import player.base.Player;
import player.base.PlayerWithMagic;
import tactics.base.Tactics;

/** 職業 僧侶 */
public class Priest extends PlayerWithMagic {
  public static ArrayList<Magic> magicList = new ArrayList<>(Arrays.asList(
    new Heal(),
    new Parryze(),
    new Poison()
    ));

  /**
   * この職業のコンストラクタ
   * 
   * @param name 名前
   */
  public Priest(String name) {
    super(name,Occupation.PRIEST, Definer.PRIEST_STATUS_RANGE);
  }

  @Override
  protected ArrayList<Magic> getMagicList(){
    return magicList;
  }

  @Override
  public void attack(Player opponent , Tactics tactics) {

    ActionNum action = tactics.selectAction();
    ArrayList<Magic> magicList = getCanUseMagicList();
    Optional<Magic> firstHealMagic = magicList.stream().filter(magic -> magic instanceof Heal).findFirst();
    ArrayList<Magic> magicListNotHeal = (ArrayList<Magic>) magicList.stream().filter(magic -> !(magic instanceof Heal)).collect(Collectors.toList());
    Magic healMagic = firstHealMagic.isPresent() ? firstHealMagic.get() : null;

    ArrayList<Magic> giveSpecialEffectMagicList = (ArrayList<Magic>) magicList.stream().filter(magic -> magic instanceof MagicWithSpecialEffect).collect(Collectors.toList());

    if(action == ActionNum.APPEAL_FOR_PEACE){
      this.appealForPeace();
    }
    else if(action == ActionNum.SPECIAL_EFFECT_MAGIC && giveSpecialEffectMagicList.size() > 0){
      giveSpecialEffectMagicList.get(random.nextInt(magicListNotHeal.size())).useMagic(this, opponent);
    }
    else if(action == ActionNum.MAGIC_HEAL && healMagic != null){
      healMagic.useMagic(this, this);
    }
    else if((action == ActionNum.MAGIC_ATTACK || action == ActionNum.MAGIC_HEAL) && magicListNotHeal.size() > 0){
      magicListNotHeal.get(random.nextInt(magicListNotHeal.size())).useMagic(this, opponent);
    }
    else{
      super.normalAttack(opponent);
    }
  }
}