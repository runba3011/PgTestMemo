public class House {
  private String ownerName;
  private String color;
  private String address;
  private boolean isDoorOpen;
  private boolean isTryingToOpenDoorNow;
  private boolean isTryingToCloseDoorNow;

  House(String ownerName, String color, String address) {
    this.ownerName = ownerName;
    this.color = color;
    this.address = address;
    isDoorOpen = false;
    isTryingToOpenDoorNow = false;
    isTryingToCloseDoorNow = false;
  }

  public String getOwnerName(){
    return this.ownerName;
  }

  public String getColor(){
    return this.color;
  }

  public String getAddress(){
    return this.address;
  }

  public boolean getIsDoorOpen(){
    return this.isDoorOpen;
  }

  public void setIsDoorOpen(boolean value){
    this.isDoorOpen  = value;
  }
}
