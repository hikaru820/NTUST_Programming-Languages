/*
 * BankingException
 * 代表銀行系統中所有自訂的業務邏輯錯誤
 * 繼承自 Exception，這是一個 Checked Exception
 */
public class BankingException extends Exception{
    /**
     * 建構子：傳遞錯誤訊息。
     * @param message 描述錯誤的字串。
     */
    public BankingException(String message) {
        super(message);
    }
}

/**
 * InsufficientFundsException
 * 專門用於當帳戶餘額不足以完成交易時的例外。
 */
class InsufficientFundsException extends BankingException {
    /**
     * 建構子：傳遞錯誤訊息。
     * @param message 描述錯誤的字串。
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}