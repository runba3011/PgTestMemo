package magic;
import magic.base.MagicWithSpecialEffect;
import specialEffect.SpecialEffectNum;

public class Parryze extends MagicWithSpecialEffect {
  public Parryze() {
    super("パライズ",10,"麻痺の効果を与える\r\n麻痺：20%の確率で麻痺で行動不能",0,0,SpecialEffectNum.PARALYSIS,20,4);
  }
}