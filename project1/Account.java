/**
 * Account 抽象類
 * 定義所有銀行帳戶的共同屬性與基本操作。
 * 這類別是所有具體帳戶類別 (Checking, Saving, CD, Loan) 的基礎。
 */
public abstract class Account {
    
    // 屬性：使用 protected 以允許子類直接存取
    protected String name;  // 帳戶名稱
    protected double balance;   // 帳戶餘額
    protected final double annualInterestRate; // 年利率，設定後不改變
    
    /**
     * 建構子：用於初始化帳戶
     * @param name 帳戶名稱
     * @param initialBalance 初始餘額
     * @param rate 帳戶的年利率 (例如 0.12 代表 12%)
     */
    public Account(String name, double initialBalance, double rate) {
        this.name = name;
        this.balance = initialBalance;
        this.annualInterestRate = rate;
    }

    // ---------------------- 核心方法 ----------------------

    /**
     * 存款操作：將金額存入帳戶。
     * 這是所有帳戶共有的基本邏輯。
     * @param amount 存款金額
     * @throws BankingException 如果交易因業務邏輯失敗（例如存款金額無效）。
     */
    public void deposit(double amount) throws BankingException{
        if (amount > 0) {
            this.balance += amount;
            System.out.println(name + ": Deposited $" + amount + ". New balance: $" + this.balance);
        } else {
            throw new BankingException("Deposit amount must be positive.");
        }
    }

    /**
     * 提款操作：從帳戶中取出金額。
     * 必須是抽象方法，因為每個帳戶類型 (Checking, Saving, CD) 的提款規則不同。
     * @param amount 提款金額
     * @throws BankingException 如果交易因業務邏輯失敗
     */
    public abstract void withdraw(double amount) throws BankingException;

    /**
     * 計算利息：計算並將累積的利息加入帳戶餘額。
     * 必須是抽象方法，因為不同帳戶的計算頻率不同 (每日 vs. 每月) 。
     */
    public abstract void compute_interest();

    // ---------------------- Getter 方法 ----------------------
    
    /**
     * 返回帳戶名稱。
     * @return 帳戶名稱字串
     */
    public String name() {
        return this.name;
    }

    /**
     * 返回帳戶餘額。
     * @return 帳戶餘額 (double)
     */
    public double balance() {
        return this.balance;
    }
}
