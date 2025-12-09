/**
 * LoanAccount 類
 * 實現貸款帳戶的具體規則：餘額為負（欠款），只能存款（償還），不能提款 。
 * 假設利息計算頻率與 SavingAccount 相同（每月）。
 */
public class LoanAccount extends Account{
    
    /**
     * 建構子：初始化貸款帳戶。
     * @param name 帳戶名稱
     * @param initialLoanAmount 初始貸款金額（請以**正數**傳入，程式內部將其轉為負餘額）
     * @param rate 帳戶的年利率
     */
    public LoanAccount(String name, double initialBalance, double rate) {
        // 貸款是負餘額 ，所以將初始金額轉為負數傳給父類
        super(name, -initialBalance, rate);
        System.out.printf("%s: Loan account created with initial debt: $%.2f\n", name, initialBalance);
    }

    /**
     * 實作存款操作：用於償還貸款 。
     * 存款會減少負餘額 (即償還欠款) 。
     * @param amount 償還金額
     * @throws BankingException 
     */
    @Override
    public void deposit(double amount) throws BankingException {
        // 使用父類別的基礎存款邏輯（減少負餘額）
        super.deposit(amount);
        System.out.println("  [Loan Payment]: Loan balance reduced.");
    }

    /**
     * 實作提款操作：
     * 貸款帳戶**不能提款**（即不能借更多錢）。
     * @param amount 提款金額
     * @throws BankingException 永遠拋出例外，表示操作不允許 [cite: 15]。
     */
    @Override
    public void withdraw(double amount) throws BankingException {
        // 拋出例外，因為貸款帳戶不允許提款。
        throw new BankingException("Loan accounts do not allow withdrawals (cannot loan more money).");
    }

    /**
     * 實作計算利息：每月計算利息 [cite: 7]。
     * 貸款的利息會**增加負餘額**（即欠款增加）。
     * 使用單利計算：月利率 = 年利率 / 12 [cite: 14]。
     */
    @Override
    public void compute_interest() {
        // 月利率
        double monthlyRate = this.annualInterestRate / 12.0;

        // 利息是根據當前的欠款 (負餘額的絕對值) 來計算的
        // 注意：這裡的 interest 應該是正數，但加到 balance 上會使 balance 更負
        double interest = Math.abs(this.balance) * monthlyRate;

        // 利息使欠款增加 (例如 -5000 + (-50) = -5050)
        this.balance -= interest;

        System.out.printf("%s: Computed monthly interest $%.2f. New debt (negative balance): $%.2f\n", name, interest, this.balance);
    }
}
