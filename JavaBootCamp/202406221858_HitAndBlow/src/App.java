import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
  static Integer NUMBER_LENGTH = 4;

  public static void main(String[] args) {
    //ユーザー入力を受け取るとき、0始まりの数字に対応するためString型で受け取る。
    //それに合わせて生成した乱数を記憶するリストもString型を持つようにする。
    ArrayList<String> randomNumberStringList = new ArrayList<String>();
    Random rand = new Random();
    while (randomNumberStringList.size() < NUMBER_LENGTH) {
      Integer randomNumber = rand.nextInt(10);
      String randomNumbeString   = randomNumber.toString();
      if (!randomNumberStringList.contains(randomNumbeString)) {
        randomNumberStringList.add(randomNumbeString);
      }
    }

    Scanner scanner = new Scanner(System.in);
    Integer inputCount = 0;
    Integer hitCount = 0;
    Integer blowCount = 0;
    while (hitCount != NUMBER_LENGTH) {
      System.out.printf("%d桁の数字を入力して下さい:", NUMBER_LENGTH);
      try {
        //0始まりの数字に対応するため、String型で受け取る。
        String inputNumberString = scanner.next();
        //Integerへのキャストを試み、失敗した場合はcatch分に入る。これにより数字が入力されたことを確認する。
        Integer inputNumber = Integer.parseInt(inputNumberString);
        if (inputNumber < 0) {
          System.out.println("正の値を入力して下さい");
        } else if (NUMBER_LENGTH != inputNumberString.length()) {
          System.out.println("桁数が間違っています");
        } else {
          inputCount ++;
          ArrayList<String> inputNumberStringList = new ArrayList<String>();
          inputNumberStringList = convertNumberStringToList(inputNumberString);

          //ヒットの項目がブローで再度見つからないようにするよう、ヒットの項目のインデックスを記憶するためのリスト
          ArrayList<Integer>hitIndexList = new ArrayList<Integer>();

          for(int i = 0;i < randomNumberStringList.size();i++){
            if(randomNumberStringList.get(i).equals(inputNumberStringList.get(i))){
              hitCount ++;
              hitIndexList.add(i);
            }
          }

          for(int i = 0;i < randomNumberStringList.size();i++){
            if(randomNumberStringList.contains(inputNumberStringList.get(i)) && !hitIndexList.contains(i)){
              blowCount ++;
            }
          }

          if(hitCount != NUMBER_LENGTH){
            System.out.printf("ヒット：%d個、ブロー：%d個\n" , hitCount , blowCount);
            hitCount = 0;
            blowCount = 0;
          }else{
            System.out.printf("おめでとう!%d回目で成功♪",inputCount);
          }
        }
      } catch (Exception e) {
        System.out.println("数字を入力して下さい");
      }
    }
  }

  public static ArrayList<String> convertNumberStringToList(String inputNumberString) {
    ArrayList<String> list = new ArrayList<String>();
    while(0 < inputNumberString.length()){
      list.add(inputNumberString.substring(0,1));
      inputNumberString = inputNumberString.substring(1,inputNumberString.length());
    }

    return list;
  }
}
