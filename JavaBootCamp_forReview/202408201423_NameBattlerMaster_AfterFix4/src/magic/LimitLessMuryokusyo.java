package magic;
import magic.base.MagicWithSpecialEffect;
import specialEffect.SpecialEffectNum;

public class LimitLessMuryokusyo extends MagicWithSpecialEffect {
  public LimitLessMuryokusyo() {
    super("無量空処",3,"廃人化の効果を与える\r\n廃人化：100%の確率で行動不能",100,150,SpecialEffectNum.CRIPPLE,99,50);
  }
}