public class WeightPrintMessageBuilder {
  public static void main(String[] args) {
    int weight = 777;
    String unit = "キロ";
    String message = String.format("僕の体重は %d キロです",weight);
    System.out.println(message);
  }
}
