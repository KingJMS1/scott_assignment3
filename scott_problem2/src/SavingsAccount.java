
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount {
	
	private static double annualInterestRate;
	static boolean interestReady = false;
	private static List<SavingsAccount> allAccounts = new ArrayList<>();
	
	private double savingsBalance;
	
	public static void modifyInterestRate(double newRate)	{
		SavingsAccount.annualInterestRate = newRate;
		interestReady = true;
	}
	
	public static void doMonthlyInterest()	{
		for (SavingsAccount acct : allAccounts)	{
			acct.calculateMonthlyInterest();
		}
	}
	
	SavingsAccount(double balance)	{
		this.savingsBalance = balance;
		allAccounts.add(this);
	}
	
 	public void calculateMonthlyInterest()	{
 		if (SavingsAccount.interestReady == false)	{
 			throw new IllegalStateException("modifyInterestRate must be run before calculateMonthlyInterest.");
 		}
		this.savingsBalance += this.savingsBalance * (SavingsAccount.annualInterestRate / 12.0);
	}
 	
 	public double getBalance()	{
 		return this.savingsBalance;
 	}
}
