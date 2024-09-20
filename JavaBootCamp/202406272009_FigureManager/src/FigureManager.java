public class FigureManager {
  public static void main(String[] args) {
    double base = 10.0;
    double height = 20.0;
    System.out.printf("底辺が %.1f cm で、高さが %.1f cm の三角形の面積 => %.1f" , base , height , Figure.calcTriangleArea(base, height));
  }
}
