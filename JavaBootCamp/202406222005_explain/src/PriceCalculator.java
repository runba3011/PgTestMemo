public class PriceCalculator {
  public static void main(String[] args) {

    int itemPrice = 9800;
    int quantity = 580;
    Order order = new Order(itemPrice, quantity);
    int wholePrice = order.calcWholePrice();
    System.out.printf(
        "%d 円の商品を %d 個注文 => %d 円（税込）%n",
        itemPrice, quantity, wholePrice);
  }
}

class Order {
  private int itemPrice;
  private int quantity;

  public Order(int itemPrice, int quantity) {
    this.itemPrice = itemPrice;
    this.quantity = quantity;
  }

  public int calcWholePrice() {
    final double TAX = 1.10;
    final int BASE_PRICE = this.itemPrice * this.quantity;
    final double QUANTITY_DISCOUNT = this.itemPrice * (this.quantity - 500) * 0.05;
    final double POSTAGE = Math.min((int) (this.itemPrice * this.quantity * 0.1), 10000);

    return Long.valueOf(Math.round((BASE_PRICE - QUANTITY_DISCOUNT) * TAX + POSTAGE)).intValue();
  }
}