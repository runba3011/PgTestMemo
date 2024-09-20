public abstract class Animal implements Barkable {
  protected String name;
  protected boolean gender;
  protected String feedingHabitat;
  protected String foodStr;
  protected String voice;

  public Animal(String name, boolean gender) {
    this.name = name;
    this.gender = gender;
    this.feedingHabitat = "";
    this.foodStr = "";
    this.voice = "";
  }

  public String toString() {
    return String.format("名前=%s::性別=%s::食性=%s", this.name, this.gender ? "オス" : "メス", this.feedingHabitat);
  }

  public void changeName(String newName) {
    this.name = newName;
  }

  public void eat() {
    System.out.println(String.format("%s は %s ........%sを食べた ........", this.name, this.feedingHabitat, this.foodStr));
  }

  public void bark(){
    System.out.println(String.format("%s %s ........", this.voice , this.voice));
  }
}