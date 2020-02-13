
public class SavingsAccountTest {
	
	
	public static void main(String[] args) {
		SavingsAccount saver1 = new SavingsAccount(2000);
		SavingsAccount saver2 = new SavingsAccount(3000);
		final String[] months = {"1", "2", "3", "3", "4", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		
		SavingsAccount.modifyInterestRate(.04);
		
		System.out.println("Account balances after each month: ");
		System.out.println("Month\tAcct 1\t\tAcct 2");
		for (String month : months)	{
			SavingsAccount.doMonthlyInterest();
			System.out.printf("%s:\t$%.02f\t$%.02f%n", month, saver1.getBalance(), saver2.getBalance());
		}
		System.out.println();
		
		SavingsAccount.modifyInterestRate(.05);
		SavingsAccount.doMonthlyInterest();
		
		System.out.println("Account balance after 5% month: ");
		System.out.println("Acct 1\t\tAcct 2");
		System.out.printf("$%.02f\t$%.02f%n", saver1.getBalance(), saver2.getBalance());
	}

}
