public class IcecreamPrices {

  private static final String ICECREAM_CONTENT_FORMAT = "%d 個盛り %s アイスの値段: %.0f 円 %n";

  public static void main(String[] args) {

    IceCream ic = new IceCream();
    ic.flavor = "ピスタチオ";
    ic.numberOfScoops = 2;

    System.out.printf(
        ICECREAM_CONTENT_FORMAT,
        ic.numberOfScoops,
        ic.flavor,
        ic.numberOfScoops * ic.pricePerScoop);

    FruitSaladWithIceCream fs = new FruitSaladWithIceCream();
    fs.flavor = "チョコレート";
    fs.numberOfScoops = 1;
    fs.gramsOfFruitSalad = 50;

    System.out.printf(
        "%d グラムのフルーツサラダと ",
        fs.gramsOfFruitSalad);
    System.out.printf(
        ICECREAM_CONTENT_FORMAT,
        fs.numberOfScoops,
        fs.flavor,
        fs.getFruitSaladWithIceCreamPrice());

    PancakeWithIceCream pancakeWithIceCream = new PancakeWithIceCream();
    pancakeWithIceCream.flavor = "バニラ";
    pancakeWithIceCream.numberOfScoops = 1;
    pancakeWithIceCream.gramsOfPancake = 75;

    System.out.printf(
        "%d グラムのパンケーキと ",
        pancakeWithIceCream.gramsOfPancake);
    System.out.printf(
        ICECREAM_CONTENT_FORMAT,
        pancakeWithIceCream.numberOfScoops,
        pancakeWithIceCream.flavor,
        pancakeWithIceCream.getPancakeWithIceCreamPrice());
  }
}

class IceCream {
  String flavor;
  int numberOfScoops;
  double pricePerScoop;

  IceCream() {
    this.pricePerScoop = 35.0;
  }

  public double getPrice(){
    return this.numberOfScoops * pricePerScoop;
  }
}

class FruitSaladWithIceCream extends IceCream {
  int gramsOfFruitSalad;
  double pricePerGram;

  FruitSaladWithIceCream() {
    super();
    pricePerGram = 0.75;
  }

  double getFruitSaladWithIceCreamPrice() {
    return gramsOfFruitSalad * pricePerGram + getPrice();
  }
}

class PancakeWithIceCream extends IceCream {
  int gramsOfPancake;
  double pricePerGram;

  PancakeWithIceCream() {
    super();
    pricePerGram = 1.25;
  }

  double getPancakeWithIceCreamPrice() {
    return gramsOfPancake * pricePerGram + getPrice();
  }
}