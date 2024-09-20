public class Character {
  public String name;
  public int maxHp;
  public int nowHp;
  public int STR;
  public int DEF;
  public int LUCK;

  Character(String name , int maxHp , int STR , int DEF , int LUCK){
    this.name = name;
    this.maxHp = maxHp;
    this.nowHp = this.maxHp;
    this.STR = STR;
    this.DEF = DEF;
    this.LUCK = LUCK;
  }

  public void attack(Character attackedCharacter){
    System.out.printf("%sの攻撃！\n",this.name);
    boolean isCritical = isCritical();
    int damage = !isCritical ? this.STR - attackedCharacter.DEF : this.STR;
    if(isCritical) System.out.print("会心の一撃！");
    if(0 < damage){
      System.out.printf("%sに %d のダメージ！\n",attackedCharacter.name , damage);
      attackedCharacter.nowHp -= damage;
    }
    else{
      System.out.println("攻撃がミス！");
    }
  }

  private boolean isCritical(){
    return BattleManager.random.nextInt(BattleManager.GENERATE_NUMBER_MAX) <= this.LUCK;
  }

  public boolean isAlive(){
    return nowHp <= 0;
  }
}
