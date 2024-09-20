package player;
import java.util.ArrayList;
import java.util.Arrays;

import definer.Definer;
import java.util.Optional;
import actionNum.ActionNum;
import magic.Magic;
import magic.Magic.MagicNum;
import player.base.Occupation;
import player.base.Player;
import specialEffect.SpecialEffectNum;
import tactics.base.Tactics;

/** 職業 五条悟 */
public class Gojo extends Player {

  /** この職業のプレイヤーが使用可能な魔法 */
  public static ArrayList<Magic> magicList = new ArrayList<>(Arrays.asList(
    new Magic(MagicNum.AKA,"術式順転 赫",1,"敵に 100 ～ 200 の防御無視ダメージ",100,200,SpecialEffectNum.NOTHING,0,0),
    new Magic(MagicNum.AO,"術式反転 蒼",1,"敵に 200 ～ 300 の防御無視ダメージ",200,300,SpecialEffectNum.NOTHING,0,0),
    new Magic(MagicNum.MURASAKI,"虚式 茈",2,"敵に 500 ～ 600 の防御無視ダメージ",500,600,SpecialEffectNum.NOTHING,0,0),
    new Magic(MagicNum.MURYOKUSYO,"無量空処",3,"廃人化の効果を与える\r\n廃人化：100%の確率で行動不能",100,150,SpecialEffectNum.CRIPPLE,99,50)
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
  public void attack(Player opponent , Tactics tactics) {
    ActionNum action = tactics.selectAction();
    ArrayList<Magic> magicList = getCanUseMagicList();
    Optional<Magic> firstMuryoKusyoMagic = magicList.stream().filter(magic -> magic.getMagicNum() == Magic.MagicNum.MURYOKUSYO).findFirst();
    Magic muryoKusyoMagic = firstMuryoKusyoMagic.isPresent() ? firstMuryoKusyoMagic.get() : null;
    if(action == ActionNum.APPEAL_FOR_PEACE){
      this.appealForPeace();
    }
    else if(action == ActionNum.SPECIAL_EFFECT_MAGIC && muryoKusyoMagic != null){
      muryoKusyoMagic.useMagic(this, opponent);
    }
    else if((action == ActionNum.MAGIC_ATTACK || action == ActionNum.MAGIC_HEAL) && 0 < magicList.size()){
      magicList.get(random.nextInt(magicList.size())).useMagic(this, opponent);
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
        // この職業はヒールを使えないためこの処理は不要
        // if (magic.getMagicNum() == Magic.MagicNum.HEAL) {
        // if (this.nowHp <= this.hp / 2) {
        // // ヒールに限っては体力が半分以下になっていないと使わないようにする。
        // canUseMagicList.add(magic);
        // }
        // continue;
        // }

        canUseMagicList.add(magic);
      }
    }
    return canUseMagicList;
  }
}