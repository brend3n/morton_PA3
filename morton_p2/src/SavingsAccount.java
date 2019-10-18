public class SavingsAccount {

    private static int annualInterestRate;
    private double savingsBalance;

    // sets the annualInterestRate to a new value.
    public static void modifyInterestRate(int newRate){
        annualInterestRate = newRate;
    }

    // constructor to set balance with parameter
    public SavingsAccount(double balance){
        this.savingsBalance = balance;
        annualInterestRate = 0;
    }

    // default constructor
    public SavingsAccount(){
        this(0);
    }

    public double getSavingsBalance(){
        return this.savingsBalance;
    }

    public void calculateMonthlyInterest(){

        double interest = annualInterestRate / 100.0;
        this.savingsBalance +=  ((savingsBalance * interest) / 12.0);

    }
}