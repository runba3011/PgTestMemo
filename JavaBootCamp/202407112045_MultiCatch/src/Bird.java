public abstract class Bird {

  protected String className;
  protected String name;

  protected Bird(String name) {
    this.name = name;
    this.className = "Bird";
  }

  public boolean isEmptyName() {
    return this.name.equals("");
  }

  public void fly() {
    System.out.format(
        "[%s] %s が飛んだ. %n",
        this.className, this.name);
  }

  public static Pigeon convertToPigeon(Bird bird) {
    return (Pigeon) bird;
  }
}