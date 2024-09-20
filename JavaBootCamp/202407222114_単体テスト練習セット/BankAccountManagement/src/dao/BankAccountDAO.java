package dao;

import constants.SettingsConstants;
import dto.BankAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベースアクセスオブジェクト
 */
public class BankAccountDAO {
    /**
     * 口座番号・暗証番号をもとに口座情報を取得
     * @param accountNo 口座番号
     * @param pin 暗証番号
     * @return 口座情報を返す。ただし、口座番号と暗証番号の組み合わせに該当する口座が存在しない場合は{@code null}を返す。
     * @throws SQLException データベースに接続できなかった場合、またはクエリの実行に失敗したい場合に例外を返す。
     */
    public BankAccount getAccountInfoByNoAndPin(int accountNo, int pin) throws SQLException {
        BankAccount result = null;

        String sql = "SELECT * FROM account_info WHERE accountNo = ? AND pin = ?";

        try(Connection con = DriverManager.getConnection(SettingsConstants.DB_CONNECTION_URL,
                    SettingsConstants.DB_USER,
                    SettingsConstants.DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement(sql)) {
            
            smt.setInt(1,accountNo);
            smt.setInt(2,pin);

            ResultSet rs = smt.executeQuery();

            if(rs.next()){
                result = new BankAccount();
                result.setAccountNo(rs.getInt("accountNo"));
                result.setHolderName(rs.getString("holderName"));
                result.setBalance(rs.getInt("balance"));
            }
        }
        return result;
    }

    /**
     * 口座番号をもとに口座情報を取得
     * @param accountNo 口座番号
     * @return　口座情報を返す。ただし、口座番号に該当する口座が存在しない場合は{@code null}を返す。
     * @throws SQLException データベースに接続できなかった場合、またはクエリの実行に失敗したい場合に例外を返す。
     */
    public BankAccount getAccountInfoByNo(int accountNo) throws SQLException {
        BankAccount result = null;

        String sql = "SELECT * FROM account_info WHERE accountNo = ?";

        try(Connection con = DriverManager.getConnection(SettingsConstants.DB_CONNECTION_URL,
                    SettingsConstants.DB_USER,
                    SettingsConstants.DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement(sql)) {
            
            smt.setInt(1,accountNo);

            ResultSet rs = smt.executeQuery();

            if(rs.next()){
                result = new BankAccount();
                result.setAccountNo(rs.getInt("accountNo"));
                result.setHolderName(rs.getString("holderName"));
                result.setBalance(rs.getInt("balance"));
            }
        }
        return result;
    }

    /**
     * 預入処理
     * @param accountNo 口座番号
     * @param amount 預入金額
     * @return 預入処理の成功状況。口座残高の更新に成功した場合は{@code true}を返す。
     * @throws SQLException データベースに接続できなかった場合、またはクエリの実行に失敗したい場合に例外を返す。
     */
    public boolean deposit(int accountNo,int amount) throws SQLException {
        boolean result = false;

        String sql = "UPDATE account_info SET balance = balance + ? WHERE accountNo = ?";

        try(Connection con = DriverManager.getConnection(SettingsConstants.DB_CONNECTION_URL,
                    SettingsConstants.DB_USER,
                    SettingsConstants.DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement(sql)) {
            
            smt.setInt(1,amount);
            smt.setInt(2,accountNo);

            int cnt = smt.executeUpdate();

            if(cnt > 0){
                result = true;
            }
        }
        return result;
    }

    /**
     * 引出処理
     * @param accountNo 口座番号
     * @param amount 引出金額
     * @return 引出処理の成功状況。口座残高の更新に成功した場合は{@code true}を返す。
     * @throws SQLException データベースに接続できなかった場合、またはクエリの実行に失敗したい場合に例外を返す。
     */
    public boolean withdraw(int accountNo,int amount) throws SQLException {
        boolean result = false;

        String sql = "UPDATE account_info SET balance = balance - ? WHERE accountNo = ?";

        try(Connection con = DriverManager.getConnection(SettingsConstants.DB_CONNECTION_URL,
                    SettingsConstants.DB_USER,
                    SettingsConstants.DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement(sql)) {
            
            smt.setInt(1,amount);
            smt.setInt(2,accountNo);

            int cnt = smt.executeUpdate();

            if(cnt > 0){
                result = true;
            }
        }
        return result;
    }

    /**
     * 口座残高の確認処理
     * @param accountNo 口座番号
     * @param amount 残高金額
     * @return 口座残高が残高金額以上の場合は{@code true}を返す。
     * @throws SQLException
     */
    public boolean hasSuficientBalance(int accountNo, int amount) throws SQLException {
        boolean result = false;

        String sql = "SELECT balance >= ? AS hasBalance FROM account_info WHERE accountNo = ?;";

        try(Connection con = DriverManager.getConnection(SettingsConstants.DB_CONNECTION_URL,
                    SettingsConstants.DB_USER,
                    SettingsConstants.DB_PASSWORD);
                PreparedStatement smt = con.prepareStatement(sql)) {
            
            smt.setInt(1,amount);
            smt.setInt(2,accountNo);

            ResultSet rs = smt.executeQuery();

            if(rs.next()){
                result = rs.getBoolean("hasBalance");
            }
        }
        return result;
    }
}
