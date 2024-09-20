public class OutOfCardsException extends Exception {
  public OutOfCardsException() {
    super("カードが残り0枚です。ゲームを続行できません！");
  }
}