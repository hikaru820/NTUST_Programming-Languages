/**
 * CDAccount 類
 * 實現定期存款的具體規則：固定期限和金額 ；期限內不能存款 ，提款有 $250 罰金 。
 * 期限結束後，提款無罰金，利息停止 。
 */
public class CDAccount extends Account{
    
    private final int durationMonths; // 存款期限 (月)
    private int elapsedMonths = 0; // 已過去的月數 (用於計算利息和判斷是否到期)
    private static final double EARLY_WITHDRAWL_FEE = 250.0;    // 提前提款費用

    /**
     * 建構子：初始化 CD 帳戶。
     * @param name 帳戶名稱
     * @param initialDeposit 初始存款金額 (固定金額) 
     * @param rate 帳戶的年利率
     * @param months 存款期限 (月) 
     */
    public CDAccount(String name, double initialDeposit, double rate, int months) {
        super(name, initialDeposit, rate);
        this.durationMonths = months;
        System.out.printf("%s: CD account created for %d months.\n", name, this.durationMonths);
    }

    /**
      * 返回 CD 帳戶的總期限（月）。
      * @return 存款期限 (月)
    */
    public int getDurationMonths() {
        return this.durationMonths;
    }

    /**
     * 檢查 CD 是否已到期 (Duration Finished) 。
     * @return 如果 elapsedMonths >= durationMonths 則為 true
     */
    public boolean isMatured() {
        return this.elapsedMonths >= this.durationMonths;
    }

    /**
     * 實作存款操作：
     * 期限內不能存款 。
     * @param amount 存款金額
     * @throws BankingException 如果在期限內嘗試存款 [cite: 9, 15]。
     */
    @Override
    public void deposit(double amount) throws BankingException {
        if (!isMatured()) {
            throw new BankingException("Cannot deposit to CD account during its duration.");
        }
        // 如果已到期，則允許存款
        super.deposit(amount);
        System.out.println("  [CD Matured]: Deposit allowed.");
    }

    /**
     * 實作提款操作：
     * 期限內提款需付 $250 費用 ；期限後無費用 。
     * @param amount 提款金額
     * @throws InsufficientFundsException 如果提款金額加上費用超過餘額。
     */
    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Withdrawal amount must be positive.");
        }

        double fee = 0.0;

        if (!isMatured()) {
            // 期限內提款，收取 $250 費用 。
            fee = EARLY_WITHDRAWL_FEE;
            System.out.printf("  [Warning]: Withdrawing before maturity (Month %d/%d). Applying penalty fee $%.2f.\n", this.elapsedMonths, this.durationMonths, fee);
        } else {
            // 期限結束後，提款無費用 。
            System.out.println("  [CD Matured]: No fee for withdrawal.");
        }

        // 檢查餘額是否足夠支付提款和可能的費用
        if (this.balance - amount - fee < 0) {
            throw new InsufficientFundsException("Cannot withdraw $" + amount + ". Insufficient funds to cover amount and fee ($" + fee + ").");
        }
        
        this.balance -= (amount + fee);
        System.out.println(name + ": Withdraw $" + amount + ". New balance: $" + this.balance);
    }

    /**
     * 實作計算利息：每月計算利息 。
     * 期限結束後，利息停止計算 。
     */
    @Override
    public void compute_interest() {
        if (isMatured()) {
            // 期限結束後，利息停止計算 。
            System.out.println(name + ": Duration finished. Interest payments stopped.");
            return;
        }
        
        // 月利率
        double monthlyRate = this.annualInterestRate / 12.0; 
        
        double interest = this.balance * monthlyRate; 
        this.balance += interest;
        this.elapsedMonths++; // 經過一個月
        
        System.out.printf("%s: Computed monthly interest $%.2f (Month %d/%d). New balance: $%.2f\n", name, interest, this.elapsedMonths, this.durationMonths, this.balance);
    }
}
