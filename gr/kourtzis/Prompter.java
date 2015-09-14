package gr.kourtzis;

import java.util.Scanner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Prompter
{
	public Prompter(Game game_obj) 
	{
		scanner_ = new Scanner(System.in);
		game_obj_ = game_obj;
		Configure();
	}

	public void Play()
	{
		while(true)
		{
			if(game_obj_.GameOver()) break;
			if(game_obj_.IsSolved(masked_answer_))
			{
				game_obj_.ChangeState(true);
				break;
			}
			Display();
			PromptForGuess();
			CheckGuess();
		}

		if(game_obj_.Found())
		{
			System.out.println("\nCongratulations. You found \"" + game_obj_.Answer() +
					           "\" with " + game_obj_.RemainingTries() +
					           " tries left\n");
		}
		else System.out.println("\nHow unfortunated :( The word you were looking for was: " + game_obj_.Answer());

		scanner_.close();
	}

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
	private void Update(int index)
	{
		game_obj_.Guess(guess_);
		char temp[] = masked_answer_.toCharArray();
		temp[index] = guess_.charAt(0);

		masked_answer_ = String.copyValueOf(temp);
	}

	private void Update(int condition, String message)
	{
		System.out.println(message);
		if(condition != LETTER_NOT_FOUND) Update(condition);
	}

	private void CheckGuess()
	{
		int end = game_obj_.Answer().indexOf(guess_);
		if(end == LETTER_NOT_FOUND) 
		{
			Update(end, "Did not found it. Sorry :(");
			game_obj_.Guess(guess_);
		} 
		else
		{
			Update(end, "Nice! we have a match");
			
			int begin = 0;
			while(end != LETTER_NOT_FOUND)
			{
				Update(end);
				// Start the new search one position 
				// after the letter was found to avoid endless-loop
				begin = ++end;
				end = game_obj_.Answer().indexOf(guess_, begin);					 
			}
		}
	}

	private void PromptForGuess()
	{
		do
		{
			System.out.print("Guess the word or enter a letter: ");
			guess_ = scanner_.nextLine();
		}
		while(!ValidatePrompt(guess_));
	}
	
	//if we found a digit or a non-word character
	// the input from the user is invalid
	private boolean ValidatePrompt(String prompt)
	{
		if(prompt.isEmpty()) return false;
		if(prompt.length() != ONE_LETTER)
		{
			// to be implemented
			// temporarily return false
			return false;
		}
		else
		{
			Pattern pattern = Pattern.compile("[^a-zA-Z]");
			Matcher matcher = pattern.matcher(prompt);

			return !matcher.find();
		}
	}

	private void Configure()
	{
		for(char tmp : game_obj_.Answer().toCharArray()) masked_answer_ += "_";
	}

	private Scanner scanner_;
	private Game game_obj_;
	private String guess_              = "";
	private String masked_answer_      = "";

	private final int LETTER_NOT_FOUND = -1;
	private final int ONE_LETTER       = 1;
}
