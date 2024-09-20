package magic.base;

import java.util.ArrayList;

import definer.Definer;
import player.base.Player;
import specialEffect.SpecialEffect;
import specialEffect.SpecialEffectNum;

/** 魔法についてのクラス */
public class MagicWithSpecialEffect extends Magic {
  /** 状態異常にする確率 */
  protected int specialEffectProbability;
  /** 魔法により発生させる状態異常 */
  protected SpecialEffectNum specialEffectNum;
  /** 状態異常が継続するターン数 */
  protected int continueTurns;

  /**
   * 魔法のコンストラクタ
   * <p>
   * 呼ぶ際には貼り付け用データ.xlsxにて作成して貼り付けること
   * 
   * @param magicNum                 魔法の番号
   * @param name                     名前
   * @param mpAmount                 必要なMP
   * @param description              説明
   * @param minDamage                最小ダメージ
   * @param maxDamage                最大ダメージ
   * @param specialEffectNum         付与する特殊効果
   * @param specialEffectProbability 特殊効果を付与する確率
   * @param continueTurns            特殊効果の継続ターン数
   */
  public MagicWithSpecialEffect(/* MagicNum magicNum, */ String name, int mpAmount, String description, int minDamage,
      int maxDamage,
      SpecialEffectNum specialEffectNum, int specialEffectProbability, int continueTurns) {
    super(name, mpAmount, description, minDamage,
        maxDamage);
    this.specialEffectNum = specialEffectNum;
    this.specialEffectProbability = specialEffectProbability;
    this.continueTurns = continueTurns;
  }

  public SpecialEffectNum getSpecialEffectNum() {
    return specialEffectNum;
  }

  /**
   * 魔法を使う
   * 
   * @param user   魔法を使うプレイヤー
   * @param target 魔法を使われるプレイヤー
   */
  @Override
  public void useMagic(Player user, Player target) {
    System.out.println(String.format("%s は %s を使った！" , user.getName() , this.name));
    user.setNowMp(user.getNowMp() - this.mpAmount);

    //最小ダメージと最大ダメージ両方とも0でない場合状態異常のみを付与するものである。
    //その場合ダメージや回復についての処理を行わない。
    if(!isNoDamageMagic()){
      giveDamage(target);
    }

    if (isSpecialEffectSuccess()) {
      giveSpecialEffect(target);
    } else {
      if (isNoDamageMagic()) {
        System.out.println(String.format("%sへの状態異常の付与に失敗した！", target.getName()));
      }
    }
  }

  /**
   * 魔法がダメージのない、状態異常を付与するだけの魔法科を取得する
   * 
   * @return
   */
  protected Boolean isNoDamageMagic() {
    return this.minDamage == 0 && this.maxDamage == 0;
  }

  /**
   * 状態異常の付与に成功したかを取得する
   * 
   * @return 状態異常の付与に成功した場合trueを返す
   */
  protected Boolean isSpecialEffectSuccess() {
    return random.nextInt(Definer.SPECIAL_EFFECT_PROBABILITY_MAX) < this.specialEffectProbability;
  }

  /**
   * 状態異常を付与する
   * 
   * @param target 状態異常を付与されるプレイヤー
   */
  protected void giveSpecialEffect(Player target) {
    ArrayList<SpecialEffect> targetEffectList = target.getSpecialEffectList();
    // 既に状態異常にかかっていた場合にはターン数を上書きする。そのため、既存のものは削除する。
    for (int i = 0; i < targetEffectList.size(); i++) {
      SpecialEffect effect = targetEffectList.get(i);
      if (effect.getSpecialEffectNum() == this.specialEffectNum) {
        targetEffectList.remove(i);
        // targetEffectListから要素を削除する分、インデックスがずれる。それに対応する。
        i--;
      }
    }
    targetEffectList.add(new SpecialEffect(this.specialEffectNum, this.continueTurns));
    System.out.println(String.format("%s に状態異常 %s を付与した！", target.getName(), this.specialEffectNum));
  }
}