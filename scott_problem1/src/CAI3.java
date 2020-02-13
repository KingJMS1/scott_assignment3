
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;



public class CAI3 {
	
	private static class Pair<T>	{
		public T a;
		public T b;
		
		Pair(T a1, T b1)	{
			this.a = a1;
			this.b = b1;
		}
		
		@Override
		public boolean equals(Object other)	{
			if (other instanceof Pair<?>)	{
				return (((Pair<?>) other).a.equals(a) && ((Pair<?>) other).b.equals(b)) || (((Pair<?>) other).a.equals(b) && ((Pair<?>) other).b.equals(a));
			}
			
			return false;
		}
	}
	
	private static final SecureRandom Generator = new SecureRandom();
	private static final Scanner scan = new Scanner(System.in);
	private static final List<Integer> answers = new ArrayList<>();
	private static final List<Pair<Integer>> questions = new ArrayList<>();
	private static int a, b;
	private static int answer;
	
	private static final String[] goodResponses = {"Very good!", "Excellent!", "Nice Work!", "Keep up the good work!"};
	private static final String[] badResponses = {"No. Please try again.", "Wrong. Try once more.", "Don't give up!", "No. Keep trying.	"};
	
	
	static void askQuestion()	{
		System.out.printf("How much is %d times %d?%n", a, b);
		
	}
	
	
	static Pair<Integer> createQuestion()	{
		int a1 = Generator.nextInt(10);
		int b1 = Generator.nextInt(10);
		Pair<Integer> pair = new Pair<>(a1, b1);
		if (questions.contains(pair))	{
			return createQuestion();
		}
		
		return pair;
	}
	
	
	static void makeQuestions()	{
		for (int i = 0; i < 10; i++)	{
			Pair<Integer> question = createQuestion();
			questions.add(question);
			answers.add(question.a * question.b);
		}
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
	
	
	static void displayCompletionMessage(double percentCorrect)	{
		System.out.println("Score: " + Double.toString(percentCorrect * 100) + "%");
		
		if (percentCorrect >= .75)	{
			System.out.println("Congratulations, you are ready to go to the next level!");
		}
		else	{
			System.out.println("Please ask your teacher for extra help.");
		}
		
	}
	
	static void quiz()	{
		makeQuestions();
		
		int numCorrect = 0;
		for (int i = 0; i < 10; i++)	{
			a = questions.get(i).a;
			b = questions.get(i).b;
			answer = a * b;
			
			askQuestion();
			int badAnswer = readResponse();
			displayResponse(badAnswer);
			if (isAnswerCorrect(badAnswer))	{
				numCorrect += 1;
			}
		}
		
		double percentCorrect = (((double) numCorrect) / 10.0);
		
		displayCompletionMessage(percentCorrect);
		
		
		System.out.println("Would you like to solve another problem set? (enter y for yes or n for no)");
		String resp = scan.nextLine();
		resp = scan.nextLine();
		
		questions.clear();
		answers.clear();
		
		if (resp.toLowerCase().contains("y"))	{
			quiz();
		}
		else	{
			
		}
	}
	
	
	public static void main(String[] args)	{
		quiz();
	}
}
