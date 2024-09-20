public class HouseKeeper {
  public static void main(String[] args){
    House house = new House("山本一郎", "赤", "愛知県名古屋市中区伏見 3-4-5");
    closeDoor(house);
    openDoor(house);
    showDetail(house);
  }

  public static void closeDoor(House house){
    System.err.printf("%s さん家のドアを閉めます\n" , house.getOwnerName());
    if(!house.getIsDoorOpen()){
      System.err.println(" => [INFO] 既にドアは閉まってます");
      return;
    }

    house.setIsDoorOpen(false);

  }

  public static void openDoor(House house){
    System.err.printf("%s さん家のドアを開けます\n" , house.getOwnerName());
    if(house.getIsDoorOpen()){
      System.err.println(" => [INFO] 既にドアは開いてます");
      return;
    }

    house.setIsDoorOpen(true);

  }

  public static void showDetail(House house){
    System.out.printf("%s さん家\n" , house.getOwnerName());
    System.out.printf("=> 色: %s\n" , house.getColor());
    System.out.printf("=> 住所: %s\n" , house.getAddress());
    System.out.printf("=> ドア: %s\n" , house.getIsDoorOpen() ? "開いてます" : "閉まってます");
  }
}
