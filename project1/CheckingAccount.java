/**
 * CheckingAccount 類
 * 實現支票帳戶的具體規則：每日利息計算和最低餘額 $1000。
 */
public class CheckingAccount extends Account {

    // 最低餘額
    private static final double MIN_BALANCE = 1000.0;

    /**
     * 建構子：呼叫父類 Account 的建構子。
     */
    public CheckingAccount(String name, double initialBalance, double rate) {
        super(name, initialBalance, rate);
    }

    /**
     * 實作提款操作：
     * - 檢查是否有足夠資金。
     * - 提款無手續費。
     * @param amount 提款金額
     * @throws InsufficientFundsException 如果提款金額導致餘額為負
     */
    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Withdrawl amount must be positive.");
        }

        // 1. check balance is enough
        if (this.balance - amount < 0) {
            throw new InsufficientFundsException(
                    "Cannot withdraw $" + amount + ". Current balance $" + this.balance + " is insufficient.");
        }

        // 2. 檢查是否違反最低餘額限制 (這裡的設計是，可以低於 $1000，但 $1000 以下的餘額不計息或有懲罰)
        // 為了簡化，我們只檢查是否會透支 (餘額小於 0)。
        // 實際銀行系統會在這裡執行額外檢查和懲罰邏輯。

        this.balance -= amount;
        System.out.println(name + ": Withdraw $" + amount + ". New balance: $" + this.balance);
    }

    /**
     * 實作計算利息：每日計算利息。
     * 使用單利計算：每日利率 = 年利率 / 365。
     */
    @Override
    public void compute_interest() {
        // 每日利率
        double dailyRate = this.annualInterestRate / 365.0;

        // 只有當餘額達到最低要求時才計算利息 (假設 $1000 以上)
        if (this.balance >= MIN_BALANCE) {
            // 假設計算一天的利息
            double interest = this.balance * dailyRate;
            this.balance += interest;
            System.out.printf("%s: Computed daily interest $%.2f. New balance: $%.2f\n", name, interest, this.balance);
        } else {
            System.out.println(name + ": Balance below minimum requirement ($" + MIN_BALANCE + "). No interest accrued.");
        }
    }
}
