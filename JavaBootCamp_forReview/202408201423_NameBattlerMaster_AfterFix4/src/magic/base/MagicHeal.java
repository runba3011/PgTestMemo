package magic.base;
import player.base.Player;

/** 回復魔法についての基底クラス */
public class MagicHeal extends Magic {
  /** 魔法による最小回復量 */
  protected int minHealAmount;
  /** 魔法による最大回復量 */
  protected int maxHealAmount;

  /**
   * 魔法のコンストラクタ
   * @param magicNum      魔法の番号
   * @param name          名前
   * @param mpAmount      必要なMP
   * @param description   説明
   * @param minHealAmount 最小回復量
   * @param maxHealAmount 最大回復量
   */
  public MagicHeal(String name, int mpAmount, String description, int minHealAmount,
      int maxHealAmount) {
    super(name, mpAmount, description, 0, 0);
    this.minHealAmount = minHealAmount;
    this.maxHealAmount = maxHealAmount;
  }

  public int getHealAmount() {
    int randomRange = this.maxHealAmount - this.minHealAmount;
    return this.minHealAmount + random.nextInt(randomRange + 1);
  }

  /**
   * 魔法を使う
   * @param user   魔法を使うプレイヤー
   * @param target 魔法を使われるプレイヤー
   */
  @Override
  public void useMagic(Player user, Player target) {
    System.out.println(String.format("%s は %s を使った！", user.getName(), this.name));
    user.setNowMp(user.getNowMp() - this.mpAmount);

    giveHeal(target);
  }

  /**
   * 魔法により対象のHPを回復する
   * 
   * @param target
   */
  protected void giveHeal(Player target) {
    int HealAmount = getHealAmount();
    System.out.println(
        String.format("%s を%d回復した！", target.getName(), Math.abs(HealAmount)));
    target.setNowHp(target.getNowHp() + HealAmount);
  }
}