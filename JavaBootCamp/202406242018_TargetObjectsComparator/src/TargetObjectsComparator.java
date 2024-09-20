public class TargetObjectsComparator {
  public static void main(String[] args) {

      String baseContent = "string";

      String targetFrontPart = "str";
      String targetBackPart = "ing";

      String targetContent =
          targetFrontPart + targetBackPart;

      TargetObject obj1 =
          new TargetObject("obj1", baseContent);

      TargetObject obj2 =
          new TargetObject("obj2", targetContent);

      obj1.showContent();
      obj2.showContent();

      TargetObject.showResultComparedObjects(obj1, obj2);
  }
}

class TargetObject {
  public String name = "";
  public String content = "";

  public TargetObject(String name, String content) {
      this.name = name;
      this.content = content;
  }

  public void showContent() {
      System.out.printf(
          "%s の値は %s です. %n",
          this.name, this.content
      );
  }

  private static boolean isSameContent(
          TargetObject obj1, TargetObject obj2) {

      return obj1.content.equals(obj2.content);
  }

  public static void showResultComparedObjects(
          TargetObject obj1, TargetObject obj2) {

      if (isSameContent(obj1, obj2)) {
          System.out.printf(
              "%s と %s は値が同じです. %n",
              obj1.name, obj2.name
          );
          return;
      }

      System.out.printf(
          "%s と %s は値が違います. %n",
          obj1.name, obj2.name
      );
  }
}