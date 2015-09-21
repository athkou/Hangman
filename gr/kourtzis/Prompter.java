package gr.kourtzis;

import java.io.IOException;
import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The prompter class. Implements the game logic. Takes input from the user, checks if it is valid
 * and determines if the user is able to solve and find the word.
 */
public class Prompter
{
	/**
	 * The constructor of the prompter class. Initializes the member variables.
	 * @param game_obj an object of the Game class
	 * @param scanner a Scanner object or taking input from the user
	 */
	public Prompter(Game game_obj, Scanner scanner)
	{
		game_obj_ = game_obj;
		scanner_ = scanner;
		Configure();
	}

	/**
	 * The member method Play() plays the game of hangman.
	 * It doesn't take any parameters and doesn't return anything.
	 */
	public void Play() throws IOException
	{
		while(!game_obj_.Found())
		{
			if(game_obj_.GameOver()) break;
			if(game_obj_.IsSolved(masked_answer_))
			{
				game_obj_.ChangeState(true);
				break;
			}
			Display();
			PromptForGuess();
			if(guess_.length() != ONE_LETTER) CheckGuess(guess_);
			else 					          CheckGuess();
		}

		if(game_obj_.Found())
		{
			System.out.println("\nCongratulations. You found \"" + game_obj_.Answer() +
					           "\" with " + game_obj_.RemainingTries() +
					           " tries left\n");
		}
		else System.out.println("\nHow unfortunate :( The word you were looking for was: " + game_obj_.Answer());

		scanner_.close();
	}

	/**
	 * The Interface of the game. It displays the masked answer,
	 * how many tries the user has left and which letters he has used so far
	 */
	public void Display()
	{
		System.out.print("\n\nWord to guess: ");
		System.out.println(masked_answer_);
		System.out.println("Remaining tries: " + game_obj_.RemainingTries());
		System.out.print("Letters used: ");
		
		char temp[] = game_obj_.Letters().toCharArray();
		for(char ch : temp) System.out.print("" + ch + ", ");
		System.out.println();
	}

	/**
	 * The member method Update() updates the masked answer
	 * that is displayed on the interface.
	 * @param index an integer that is used as an index for an array.
	 */
	private void Update(int index)
	{
		game_obj_.Guess(guess_);
		char temp[] = masked_answer_.toCharArray();
		temp[index] = guess_.charAt(0);

		masked_answer_ = String.copyValueOf(temp);
	}

	/**
	 * The member method Update() updates the masked answer
	 * that is displayed on the interface. In addition it prints a message to the user.
	 * @param condition an integer that is used as an index for an array.
	 * @param message A string variable that contains a message.
	 */
	private void Update(int condition, String message)
	{
		System.out.println(message);
		if(condition != LETTER_NOT_FOUND) Update(condition);
	}

	/**
	 * The member method checks if the word
	 * the user typed matches the word we are looking for.
	 * @param word A string variable containing the word the user gave as an answer
	 */
	private void CheckGuess(String word)
	{
		if(game_obj_.IsSolved(word)) game_obj_.ChangeState(true);
		else
		{
			System.out.println("The word \"" + word + "\" is wrong :(");
			game_obj_.Miss();
		}
	}

	/**
	 * The member method checks if the word contains the letter
	 * that was given by the user.
	 */
	private void CheckGuess()
	{
		int end = game_obj_.Answer().indexOf(guess_);
		if (end == LETTER_NOT_FOUND)
		{
			Update(end, "Did not found it. Sorry :(");
			game_obj_.Guess(guess_);
		}
		else
		{
			Update(end, "Nice! we have a match");

			int begin = 0;
			while (end != LETTER_NOT_FOUND)
			{
				Update(end);
				// Start the new search one position
				// after the letter was found to avoid endless-loop
				begin = ++end;
				end = game_obj_.Answer().indexOf(guess_, begin);
			}
		}
	}

	/**
	 * The member method PromptForGuess() asks the user to type a letter
	 * and waits for his response
	 */
	private void PromptForGuess()
	{
		do
		{
			System.out.print("Guess the word or enter a letter: ");
			guess_ = scanner_.nextLine();
		}
		while(!ValidatePrompt(guess_));
	}

	/**
	 * The member method ValidatePrompt() validates the input from the user.
	 * if we found a digit or a non-word character the input from the user is invalid
	 * @param prompt a String variable. Contains the letter from the user.
	 * @return returns true or false
	 */
	private boolean ValidatePrompt(String prompt)
	{
		if(prompt.isEmpty()) return false;

		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Matcher matcher = pattern.matcher(prompt);

		return !matcher.find();
	}

	/**
	 * The member method Configure masks the answer.
	 */
	private void Configure()
	{
		for(char tmp : game_obj_.Answer().toCharArray()) masked_answer_ += "_";
	}

	private Scanner scanner_;  						/**< A private Scanner variable. Is used to take input from the user */
	private Game game_obj_;    						/**< The private Game object variable */
	private String guess_              = "";		/**< A private String variable. Stores the answer from the user */
	private String masked_answer_      = "";        /**< A private String variable. It masks the answer */

	private final int LETTER_NOT_FOUND = -1;
	private final int ONE_LETTER       = 1;
}
