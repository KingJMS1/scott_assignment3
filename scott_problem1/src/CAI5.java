
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;



public class CAI5 {
	
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
				return (((Pair<?>) other).a.equals(a) && ((Pair<?>) other).b.equals(b));
			}
			
			return false;
		}
	}
	
	private static final SecureRandom Generator = new SecureRandom();
	private static final Scanner scan = new Scanner(System.in);
	
	// Probably should have just made a problem class but oh well, this works well enough
	private static final List<Pair<Integer>> questions = new ArrayList<>();
	private static final List<Integer> types = new ArrayList<>();
	
	private static int a, b;
	private static int difficulty;
	private static double answer;
	private static int problemType;
	
	// Unfortunately java does not want to let me make an array of functions the way I would normally do it
	private static final List<Function<Pair<Integer>, Double>> answerCalculators = new ArrayList<>();
	private static final String[] goodResponses = {"Very good!", "Excellent!", "Nice Work!", "Keep up the good work!"};
	private static final String[] badResponses = {"No. Please try again.", "Wrong. Try once more.", "Don't give up!", "No. Keep trying.	"};
	private static final String[] questionEnds = {"%d plus %d?%n", "%d minus %d?%n", "%d times %d?%n", "%d divided by %d?%n"};
	
	
	static void askQuestion()	{
		System.out.printf("How much is " + questionEnds[problemType], a, b);
		
	}
	
	
	static int generateQuestionArgument()	{
		int max = 1;
		for (int i = 0; i < difficulty; i++)	{
			max *= 10;
		}
		
		return Generator.nextInt(max);
	}
	
	
	static int divZeroGenerateQuestionArgument()	{
		int max = 1;
		for (int i = 0; i < difficulty; i++)	{
			max *= 10;
		}
		
		max -= 1;
		return Generator.nextInt(max) + 1;
	}
	
	
	static Pair<Integer> createQuestion()	{
		int a1 = generateQuestionArgument();
		int b1 = generateQuestionArgument();
		Pair<Integer> pair = new Pair<>(a1, b1);
		if (questions.contains(pair))	{
			return createQuestion();
		}
		
		return pair;
	}
	
	static Pair<Integer> divZeroRegen()	{
		int a1 = generateQuestionArgument();
		int b1 = divZeroGenerateQuestionArgument();
		Pair<Integer> pair = new Pair<>(a1, b1);
		if (questions.contains(pair))	{
			return divZeroRegen();
		}
		
		return pair;
	}
	
	
	static void makeQuestions()	{
		for (int i = 0; i < 10; i++)	{
			Pair<Integer> question = createQuestion();
			
			if (problemType == 3 && question.b == 0)	{
				question = divZeroRegen();
			}
			
			questions.add(question);
			if (problemType == 4)	{
				if (question.b == 0)	{
					types.add(Generator.nextInt(3));
				}
				else	{
					types.add(Generator.nextInt(4));
				}
			}
			else	{
				types.add(problemType);
			}
		}
	}
	
	
	static double readResponse()	{
		return scan.nextDouble();
	}
	
	
	
	static boolean isAnswerCorrect(double badAnswer)	{
		return (answer - .01 < badAnswer) && (answer + .01 > badAnswer);
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
	
	
	static void displayResponse(double badAnswer)	{
		
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
	
	static void readDifficulty()	{
		System.out.println("Enter a difficulty level between 1 and 4.");
		difficulty = scan.nextInt();
	}
	
	static void readProblemType()	{
		System.out.println("Enter a problem type (types: 1 = addition, 2 = multiplication, 3 = subtraction, 4 = divison, 5 = random mix.)");
		problemType = scan.nextInt() - 1;
	}
	
	static void quiz()	{
		
		if (answerCalculators.isEmpty()) {
			answerCalculators.add((Pair<Integer> x) -> (double)(x.a + x.b));
			answerCalculators.add((Pair<Integer> x) -> (double)(x.a - x.b));
			answerCalculators.add((Pair<Integer> x) -> (double)(x.a * x.b));
			answerCalculators.add((Pair<Integer> x) -> ((double)x.a) / ((double)x.b));
		}
		
		readDifficulty();
		readProblemType();
		
		makeQuestions();
		
		int numCorrect = 0;
		for (int i = 0; i < 10; i++)	{
			problemType = types.get(i);
			a = questions.get(i).a;
			b = questions.get(i).b;
			answer = answerCalculators.get(problemType).apply(questions.get(i));
			
			askQuestion();
			double badAnswer = readResponse();
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
		types.clear();
		
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
