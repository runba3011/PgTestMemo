import java.sql.SQLException;
import dao.BankAccountDAO;
import dto.BankAccount;
import input.InputHandler;
import constants.MessageConstants;
import constants.TransactionConstants;

/**
 * ATMクラス
 */
public class Atm {
  /** 現在使用しているユーザーの講座情報 */
  private BankAccount userBankAccount = null;
  /** データベースアクセスオブジェクトのインスタンス */
  private BankAccountDAO bankAccountDAO = new BankAccountDAO();
  /** 入力共通クラスのインスタンス */
  private InputHandler inputHandler = new InputHandler();
  private final int ACCOUNT_NO_DIGITS = 8;
  private final int PIN_DIGITS = 4;

  /** ATMの機能を実行する */
  public void startAtm() {
    System.out.print(MessageConstants.INFO_START_SYSTEM);

    try {
      authenticateUser();
      menu();
    } catch (SQLException e) {
      System.out.print(MessageConstants.WARNING_SQL_ERROR);
    }

    System.out.print(MessageConstants.INFO_END_SYSTEM);
  }

  /** 講座情報認証を行う */
  private void authenticateUser() throws SQLException {
    //口座情報認証で異常が発生した場合に再度入力させるためのループ
    while (userBankAccount == null) {
      int accountNo = inputHandler.inputNumber(MessageConstants.INPUT_ACCOUNTNO, ACCOUNT_NO_DIGITS);
      int pin = inputHandler.inputNumber(MessageConstants.INPUT_PIN, PIN_DIGITS);
      userBankAccount = bankAccountDAO.getAccountInfoByNoAndPin(accountNo, pin);
      if (userBankAccount == null) {
        System.out.print(MessageConstants.AUTHENTICATION_FAIL);
      } else {
        System.out.print(MessageConstants.AUTHENTICATION_SUCCESS);
      }
    }
  }

  /**　メニューの表示および各機能を実行する */
  private void menu() throws SQLException {
    MENU_LOOP: while (true) {
      System.out.print(MessageConstants.TRANSACTION_MENU);
      int actionNumber = inputHandler.inputNumber(MessageConstants.INPUT_TRANSACTION_NUMBER);
      switch (actionNumber) {
        case TransactionConstants.DEPOSIT:
          deposit();
          break;
        case TransactionConstants.WITHDRAW:
          withdraw();
          break;
        case TransactionConstants.BALANCE:
          balance();
          break;
        case TransactionConstants.TRANSFER:
          transfer();
          break;
        case TransactionConstants.END_TRANSACTION:
          //ループを抜けるときのため他のものと異なり専用の関数を作成していない。
          break MENU_LOOP;
        default:
          System.out.print(MessageConstants.WARNING_INVALID_TRANSACTION_NUMBER);
          break;
      }
    }
  }

  /** 講座番号をもとに口座情報をリフレッシュする */
  private void refreshAccountInfo() throws SQLException{
    userBankAccount = bankAccountDAO.getAccountInfoByNo(userBankAccount.getAccountNo());
  }

  /** 各機能を実行した後の残高を表示する */
  private void printBalanceAfterAction(){
    System.out.print(String.format(MessageConstants.OUTPUT_BALANCE_AFTER_TRANSACTION, userBankAccount.getBalance()));
  }

  /** 預け入れを行う */
  private void deposit() throws SQLException {
    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_START);
    System.out.print(MessageConstants.TRANSACTION_HEADER_DEPOSIT);

    int amount = inputHandler.inputPositiveNumber(MessageConstants.INPUT_DEPOSIT_AMOUNT);
    bankAccountDAO.deposit(userBankAccount.getAccountNo(), amount);
    refreshAccountInfo();
    printBalanceAfterAction();

    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_END);
  }

  /** 引出を行う */
  private void withdraw() throws SQLException {
    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_START);
    System.out.print(MessageConstants.TRANSACTION_HEADER_WITHDRAW);

    int amount = inputHandler.inputPositiveNumber(MessageConstants.INPUT_WITHDRAW_AMOUNT);
    if(bankAccountDAO.hasSuficientBalance(userBankAccount.getAccountNo(), amount)){
      bankAccountDAO.withdraw(userBankAccount.getAccountNo(), amount);
      refreshAccountInfo();
      printBalanceAfterAction();
    }
    else{
      System.out.print(MessageConstants.WARNING_INSUFFICIENT_BALANCE);
    }

    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_END);
  }

  /** 残高照会を行う */
  private void balance() throws SQLException {
    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_START);
    System.out.print(MessageConstants.TRANSACTION_HEADER_BALANCE);

    refreshAccountInfo();
    //この場合のみ、「お取引後残高」ではなく「残高」と表示するためprintBalanceAfterAction関数は使用しない。
    System.out.print(String.format(MessageConstants.OUTPUT_BALANCE, userBankAccount.getBalance()));

    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_END);
  }

  /** 振り込みを行う */
  private void transfer() throws SQLException {
    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_START);
    System.out.print(MessageConstants.TRANSACTION_HEADER_TRANSFER);

    int targetAccountNo = inputHandler.inputNumber(MessageConstants.INPUT_TRANSFER_DESTINATION, ACCOUNT_NO_DIGITS);
    BankAccount targetBankAccount = bankAccountDAO.getAccountInfoByNo(targetAccountNo);
    if(targetBankAccount != null){
      int transferAmount = inputHandler.inputPositiveNumber(MessageConstants.INPUT_TRANSFER_AMOUNT);
      if(bankAccountDAO.hasSuficientBalance(userBankAccount.getAccountNo(), transferAmount)){
        bankAccountDAO.withdraw(userBankAccount.getAccountNo(), transferAmount);
        bankAccountDAO.deposit(targetBankAccount.getAccountNo(), transferAmount);
        refreshAccountInfo();
        System.out.print(String.format(MessageConstants.OUTPUT_RECEIVER_NAME, targetBankAccount.getHolderName()));
        System.out.print(String.format(MessageConstants.OUTPUT_TRANSFER_AMOUNT, transferAmount));
        printBalanceAfterAction();
      }
      else{
        System.out.print(MessageConstants.WARNING_INSUFFICIENT_BALANCE);
      }
    }
    else{
      System.out.print(MessageConstants.WARNING_INVALID_TRANSFER_DESTINATION);
    }
    System.out.print(MessageConstants.TRANSACTION_SEPARATOR_END);
  }
}
