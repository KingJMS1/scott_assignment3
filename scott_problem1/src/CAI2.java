
import java.security.SecureRandom;
import java.util.Scanner;

public class CAI2 {
	
	private static final SecureRandom Generator = new SecureRandom();
	private static final Scanner scan = new Scanner(System.in);
	private static int answer;
	private static int a, b;
	
	private static final String[] goodResponses = {"Very good!", "Excellent!", "Nice Work!", "Keep up the good work!"};
	private static final String[] badResponses = {"No. Please try again.", "Wrong. Try once more.", "Don't give up!", "No. Keep trying.	"};
	
	
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
	
	static void displayRandomResponse(String[] responses)	{
		// When you map the numbers from 0 - 3 to strings via an array but have to use a switch anyway due to assignment requirements
		switch (Generator.nextInt(4))	{
		case 0:
			System.out.println(responses[0]);
			break;
		case 1:
			System.out.println(responses[1]);
			break;
		case 2:
			System.out.println(responses[2]);
			break;
		case 3:
			System.out.println(responses[3]);
			break;
		}
	}
	
	static void displayCorrectResponse()	{
		displayRandomResponse(goodResponses);
	}
	
	
	static void displayIncorrectResponse()	{
		displayRandomResponse(badResponses);
	}
	
	
	static void displayResponse(int badAnswer)	{
		
		if (isAnswerCorrect(badAnswer))	{
			displayCorrectResponse();
		}
		else	{
			displayIncorrectResponse();
		}
		
	}
	
	
	static void redo()	{
		askQuestion();
		
		int badAnswer = readResponse();
		
		displayResponse(badAnswer);
		
		if (isAnswerCorrect(badAnswer))	{
			
		}
		else	{
			redo();
		}
	}
	
	
	static void quiz()	{
		makeQuestion();
		askQuestion();
		
		int badAnswer = readResponse();
		
		displayResponse(badAnswer);
		
		if (isAnswerCorrect(badAnswer))	{
			
		}
		else	{
			redo();
		}
	}
	
	
	public static void main(String[] args)	{
		quiz();
	}
}
