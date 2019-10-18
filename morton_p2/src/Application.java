public class Application {

    public static void main(String args[]){

        // instantiate two objects
        SavingsAccount saver1 =  new SavingsAccount(2000.00);
        SavingsAccount saver2 =  new SavingsAccount(3000.00);

        SavingsAccount.modifyInterestRate(4);

        // Print balances with monthly interest after each month
        for(int i = 0; i < 12; i++){

            saver1.calculateMonthlyInterest();
            saver2.calculateMonthlyInterest();
            System.out.printf("Month(%d)\nsaver1 balance: %.2f\nsaver2 balance: %.2f\n\n",i+1,saver1.getSavingsBalance(), saver2.getSavingsBalance());
        }

        System.out.println("\n\n\n");

        SavingsAccount.modifyInterestRate(5);

        saver1.calculateMonthlyInterest();
        saver2.calculateMonthlyInterest();

        System.out.println("Next Month with Interest Rate = 5%\n");
        System.out.printf("saver1 balance: %.2f\nsaver2 balance: %.2f\n",saver1.getSavingsBalance(), saver2.getSavingsBalance());
    }

}
