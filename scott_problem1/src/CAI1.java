
import java.security.SecureRandom;
import java.util.Scanner;

public class CAI1 {
	
	private static final SecureRandom Generator = new SecureRandom();
	private static final Scanner scan = new Scanner(System.in);
	private static int answer;
	private static int a, b;
	
	
	static void askQuestion()	{
		System.out.printf("How much is %d times %d?%n", a, b);
	}
	
	
	static void makeQuestion()	{
		a = Generator.nextInt(10);
		b = Generator.nextInt(10);
		answer = a * b;
	}
	
	
	static int readResponse()	{
		return scan.nextInt();
	}
	
	
	static boolean isAnswerCorrect(int badAnswer)	{
		return badAnswer == answer;
	}
	
	
	static void displayCorrectResponse()	{
		System.out.println("Very good!");
	}
	
	
	static void displayIncorrectResponse()	{
		System.out.println("No. Please try again.");
	}
	
	
	static void displayResponse(int badAnswer)	{
		
		if (isAnswerCorrect(badAnswer))	{
			CAI1.displayCorrectResponse();
		}
		else	{
			CAI1.displayIncorrectResponse();
		}
		
	}
	
	
	static void redo()	{
		askQuestion();
		
		int badAnswer = CAI1.readResponse();
		
		CAI1.displayResponse(badAnswer);
		
		if (CAI1.isAnswerCorrect(badAnswer))	{
			
		}
		else	{
			redo();
		}
	}
	
	
	static void quiz()	{
		makeQuestion();
		askQuestion();
		
		int badAnswer = CAI1.readResponse();
		
		CAI1.displayResponse(badAnswer);
		
		if (CAI1.isAnswerCorrect(badAnswer))	{
			
		}
		else	{
			redo();
		}
	}
	
	
	public static void main(String[] args)	{
		quiz();
	}
}
