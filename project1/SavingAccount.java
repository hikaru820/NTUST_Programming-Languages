/**
 * SavingAccount 類
 * 實現儲蓄帳戶的具體規則：每月利息計算和交易手續費。
 */
public class SavingAccount extends Account{
    
    private int transactionCount = 0; // 當月交易次數
    private static final int FREE_TRANSACTIONS = 3; // 每月免費交易次數
    private static final double TRANSACTION_FEE = 1.0; // 每筆交易手續費

    /**
     * 建構子：呼叫父類 Account 的建構子。
     */
    public SavingAccount(String name, double initialBalance, double rate) {
        super(name, initialBalance, rate);
    }

    /**
     * 私有方法：處理交易次數和費用
     */
    private void processTransactionFee() {
        this.transactionCount++;

        if (this.transactionCount > FREE_TRANSACTIONS) {
            this.balance -= TRANSACTION_FEE;
            System.out.printf("  [Fee Charged]: Transaction %d. Applied $%.2f fee.\n",
                    this.transactionCount, TRANSACTION_FEE);
        } else {
            System.out.printf("  [Fee Waived]: Transaction %d (Free transaction %d of %d).\n",
                    this.transactionCount, this.transactionCount, FREE_TRANSACTIONS);
        }
    }
    
    /**
     * 實作存款操作：
     * - 執行基本存款。
     * - 處理交易費用。
     * @throws BankingException 
     */
    @Override
    public void deposit(double amount) throws BankingException {
        super.deposit(amount);
        processTransactionFee();
    }

    /**
     * 實作提款操作：
     * - 檢查資金是否足夠支付提款和可能的費用。
     * - 處理交易費用。
     * @param amount 提款金額
     * @throws InsufficientFundsException 如果提款金額加上費用超過餘額
     */
    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Withdrawl amount must be positive.");
        }

        // 預計的費用 (如果超過免費次數)
        double potentialFee = (this.transactionCount + 1 > FREE_TRANSACTIONS) ? TRANSACTION_FEE : 0.0;

        // 檢查餘額是否足夠支付提款和費用
        if (this.balance - amount - potentialFee < 0) {
            throw new InsufficientFundsException("Cannot withdrawl $" + amount
                    + ". Insufficient funds to cover amount and potential fee ($\" + potentialFee + \").");
        }

        this.balance -= amount;
        processTransactionFee();

        System.out.println(name + ": Withdraw $" + amount + ". New balance: $" + this.balance);
    }
    
    /**
     * 實作計算利息：每月計算利息。
     * - 使用單利計算：月利率 = 年利率 / 12。
     * - 計算利息後，重置交易次數。
     */
    @Override
    public void compute_interest() {
        // 月利率
        double monthlyRate = this.annualInterestRate / 12.0;

        double interest = this.balance * monthlyRate;
        this.balance += interest;

        System.out.printf("%s: Computed monthly interest: $%.2f. New balance: $%.2f\n", name, interest, this.balance);
        
        // 每月結算後，重置交易次數
        this.transactionCount = 0;
        System.out.println("  [Reset]: Monthly transaction count reset to 0.");
    }
}
