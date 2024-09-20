public class StatusReader {

  enum StatusType {
    WAIT, PROGRESS, DONE, KEEP, PROBLEM,
  }

  public static void main(String[] args) {

    // 1回目
    String errorMessage = "読み込みエラー";
    StatusType status = StatusType.PROGRESS;

    if (hasError(errorMessage, status)) {
      showErrorMessage(errorMessage);
    }

    // 2回目
    errorMessage = null;
    status = StatusType.DONE;

    if (hasError(errorMessage, status)) {
      showErrorMessage(errorMessage);
    }
  }

  public static boolean hasError(String errorMessage, StatusType status) {
    return status == StatusType.KEEP
        || status == StatusType.PROBLEM
        || errorMessage != null;
  }

  public static void showErrorMessage(String errorMessage) {
    System.out.println("エラーです.");

    if (errorMessage != null) {
      System.out.printf(
          " => %s %n",
          errorMessage);
    }
    System.out.println();
  }
}