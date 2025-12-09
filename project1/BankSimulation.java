@FunctionalInterface
interface BankingAction {
    void run() throws BankingException;
}

/**
 * BankSimulation 類
 * 這是主測試程式，用於創建所有帳戶類型並測試所有方法及錯誤情況 。
 */
public class BankSimulation {

    /**
     * 通用測試方法，用於包裹可能拋出例外的操作。
     */
    private static void testOperation(String description, BankingAction action) {
        System.out.println("\n--- " + description + " ---");
        try {
            action.run();
        } catch (BankingException e) {
            // 捕捉到預期的或非預期的例外。
            System.err.println("  [Transaction Failed]: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("  [FATAL ERROR]: Unexpected exception: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=====================================================================");
        System.out.println("                 CS4001301 Bank Simulation Test                      ");
        System.out.println("=====================================================================");

        // ------------------ 1. 創建所有帳戶 (滿足創建至少一個的要求 [cite: 16]) ------------------
        CheckingAccount checkAcc = new CheckingAccount("Cathy's Checking", 2500.0, 0.05); // 5% 年利率
        SavingAccount saveAcc = new SavingAccount("Sam's Savings", 500.0, 0.04);         // 4% 年利率
        CDAccount cdAcc = new CDAccount("Tom's CD", 5000.0, 0.06, 12);                   // 6% 年利率, 12 個月
        LoanAccount loanAcc = new LoanAccount("Larry's Loan", 10000.0, 0.08);           // 8% 年利率, 初始借款 $10000

        // ------------------ 2. 測試 CheckingAccount ------------------
        testOperation("Testing Checking Account (Deposit, Interest, Withdraw, Error)", () -> {
            checkAcc.deposit(100.0);
            checkAcc.compute_interest(); 
            checkAcc.withdraw(500.0);
            
            // 錯誤案例：嘗試提款太多錢 (餘額約 2100，提款 3000)
            checkAcc.withdraw(3000.0); 
        });

        // ------------------ 3. 測試 SavingAccount (測試費用和重置) ------------------
        testOperation("Testing Saving Account (Fees and Reset)", () -> {
            // 交易 1 (免費)
            saveAcc.deposit(100.0);
            // 交易 2 (免費)
            saveAcc.withdraw(50.0);
            // 交易 3 (免費)
            saveAcc.deposit(50.0);
            
            // 交易 4 (收費 $1)
            saveAcc.withdraw(20.0);
            
            // 錯誤案例：嘗試提款太多錢 (餘額約 580，提款 600 + $1 費用)
            saveAcc.withdraw(600.0); 
            
            // 計算利息並重置交易次數
            saveAcc.compute_interest();
        });
        
        // ------------------ 4. 測試 CDAccount (測試費用和到期) ------------------
        testOperation("Testing CD Account (Early Withdrawal Fee and Maturity)", () -> {
            // 錯誤案例：期限內不能存款
            cdAcc.deposit(100.0); 
            
            // 錯誤案例：期限內提款，會收 $250 費用
            cdAcc.withdraw(100.0); 
            
            // 模擬 CD 帳戶經過 12 個月 (利息計算 12 次)
            for (int i = 0; i < cdAcc.getDurationMonths(); i++) {
                cdAcc.compute_interest();
            }
            
            // 期限結束，利息停止 
            cdAcc.compute_interest(); 
            
            // 期限結束，提款 w/o fee 
            cdAcc.withdraw(100.0);
            
            // 期限結束，現在可以存款
            cdAcc.deposit(500.0);
        });
        
        // ------------------ 5. 測試 LoanAccount ------------------
        testOperation("Testing Loan Account (Repay and No Withdrawal)", () -> {
            loanAcc.deposit(500.0); // 償還 $500
            loanAcc.compute_interest(); // 計算利息 (欠款增加)
            
            // 錯誤案例：不能提款（借更多錢）
            loanAcc.withdraw(100.0); 
        });
        
        System.out.println("\n=====================================================================");
        System.out.println("                 FINAL ACCOUNT BALANCES                              ");
        System.out.println("=====================================================================");
        System.out.printf("Checking Balance: $%.2f\n", checkAcc.balance());
        System.out.printf("Saving Balance:   $%.2f\n", saveAcc.balance());
        System.out.printf("CD Balance:       $%.2f\n", cdAcc.balance());
        System.out.printf("Loan Balance:     $%.2f (Debt)\n", loanAcc.balance());
    }
}