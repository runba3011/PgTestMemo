package tactics;

import java.util.ArrayList;
import java.util.Arrays;

import tactics.base.Tactics;

public class AllTactics{
  public final static ArrayList<Tactics> tacticsList = new ArrayList<>(Arrays.asList(new RandomMove(), new ConversingWithMuscles(),
      new UseMagic(), new NonViolence(), new GiveSpecialEffect() , new BullyingWeak()));

  /**
   * インスタンス化を避けるためのコンストラクタ
   */
  private AllTactics(){

  }
}
