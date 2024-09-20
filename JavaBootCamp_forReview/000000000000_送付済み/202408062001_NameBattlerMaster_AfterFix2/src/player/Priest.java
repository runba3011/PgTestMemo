package player;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Optional;
import java.util.stream.Collectors;

import actionNum.ActionNum;
import magic.Magic;
import magic.Magic.MagicNum;
import player.base.Occupation;
import player.base.Player;
import specialEffect.SpecialEffectNum;
import tactics.base.Tactics;

/** 職業 僧侶 */
public class Priest extends Player {
  /** この職業のプレイヤーのステータスの上下限値 */
  private static StatusRange statusRange = new StatusRange(80, 200, 20, 50, 10, 70, 10, 70, 1, 100,
      20, 60);

  /** この職業のプレイヤーが使用可能な魔法 */
  public static ArrayList<Magic> magicList = new ArrayList<>(Arrays.asList(
    new Magic(MagicNum.HEAL,"ヒール",20,"HP を 50 回復",-50,-50,SpecialEffectNum.AUTO_HEAL,90,3),
    new Magic(MagicNum.PARRYZE,"パライズ",10,"麻痺の効果を与える\r\n麻痺：20%の確率で麻痺で行動不能",0,0,SpecialEffectNum.PARALYSIS,20,4),
    new Magic(MagicNum.POISON,"ポイズン",10,"敵に 10 ～ 30 の防御無視ダメージ",10,30,SpecialEffectNum.POISON,40,5)
  ));

  /**
   * この職業のコンストラクタ
   * 
   * @param name 名前
   */
  public Priest(String name) {
    super(name,Occupation.PRIEST, statusRange);
  }

  @Override
  public void attack(Player opponent , Tactics tactics) {

    ActionNum action = tactics.selectAction();
    ArrayList<Magic> magicList = getCanUseMagicList();
    Optional<Magic> firstHealMagic = magicList.stream().filter(magic -> magic.getMagicNum() == Magic.MagicNum.HEAL).findFirst();
    ArrayList<Magic> magicListNotHeal = (ArrayList<Magic>) magicList.stream().filter(magic -> magic.getMagicNum() != Magic.MagicNum.HEAL).collect(Collectors.toList());
    Magic healMagic = firstHealMagic.isPresent() ? firstHealMagic.get() : null;

    ArrayList<Magic> giveSpecialEffectMagicList = (ArrayList<Magic>) magicList.stream().filter(magic -> magic.getSpecialEffectNum() != SpecialEffectNum.NOTHING).collect(Collectors.toList());

    if(action == ActionNum.APPEAL_FOR_PEACE){
      this.appealForPeace();
    }
    else if(action == ActionNum.SPECIAL_EFFECT_MAGIC && 0 < giveSpecialEffectMagicList.size()){
      giveSpecialEffectMagicList.get(random.nextInt(0,magicListNotHeal.size())).useMagic(this, opponent);
    }
    else if(action == ActionNum.MAGIC_HEAL && healMagic != null){
      healMagic.useMagic(this, this);
    }
    else if((action == ActionNum.MAGIC_ATTACK || action == ActionNum.MAGIC_HEAL) && 0 < magicListNotHeal.size()){
      magicListNotHeal.get(random.nextInt(0,magicListNotHeal.size())).useMagic(this, opponent);
    }
    else{
      this.normalAttack(opponent);
    }
  }

  /**
   * 通常攻撃を行う
   * 通常攻撃は各職業で共通処理だが、
   * attack関数はオーバーライドして作成する必要があるため、基底クラス側では作成しない。
   * コピペして作成しやすいよう、別関数に分離した。
   * 
   * @param opponent 攻撃対象のプレイヤー
   */
  private void normalAttack(Player opponent) {
    System.out.printf("%sの攻撃！\n", this.name);
    boolean isCritical = isCritical();
    int damage = !isCritical ? this.str - opponent.getDef() : this.str;
    if (isCritical)
      System.out.print("会心の一撃！");
    if (0 < damage) {
      System.out.printf("%sに %d のダメージ！\n", opponent.getName(), damage);
      opponent.setNowHp(opponent.getNowHp() - damage);
    } else {
      System.out.println("攻撃がミス！");
    }
  }

  /**
   * 使用可能な魔法をすべて取得する。
   * 魔法を使用可能な職業での共通処理だが、
   * Playerクラスに作成すると魔法を使えない職業も魔法を使えるような形となってしまうため
   * 魔法を使用可能な各職業のクラスにそれぞれ同じものを作成する。
   * 
   * @return 使用可能な魔法のリスト
   */
  protected ArrayList<Magic> getCanUseMagicList() {
    ArrayList<Magic> canUseMagicList = new ArrayList<>();
    for (Magic magic : magicList) {
      if (magic.getMpAmount() <= this.nowMp) {
        if (magic.getMagicNum() == Magic.MagicNum.HEAL) {
          if (this.nowHp <= this.hp / 2) {
            // ヒールに限っては体力が半分以下になっていないと使わないようにする。
            canUseMagicList.add(magic);
          }
          continue;
        }

        canUseMagicList.add(magic);
      }
    }
    return canUseMagicList;
  }
}