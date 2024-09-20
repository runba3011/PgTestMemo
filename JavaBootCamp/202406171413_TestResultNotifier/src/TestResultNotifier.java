import java.util.Scanner;

public class TestResultNotifier {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("100点満点中、何点だったかを回答してください");
        System.out.println("数学のテストの得点は？:");
        int point = scan.nextInt();
        String result = 70 <= point ? "合格" : "不合格";
        System.out.printf("数学のテストは %s です", result);
    }
}
