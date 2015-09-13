package gr.kourtzis;

import java.io.Console;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Prompter
{
	public Prompter(Game game_obj) 
	{
		console_ = System.console();
		if(console_ == null)
		{
			System.err.println("No console!");
			System.exit(1);
		}

		game_obj_ = game_obj; 
		guess_ = "";
	}

	public void Display()
	{
		System.out.print("Word to guess: ");

		for(int it : game_obj_.Answer().toCharArray())
			System.out.print("_"); 

		System.out.println("\n");

		PromptForGuess();
		System.out.println("You guessed: " + guess_);
		CheckGuess();
	}
	private void CheckGuess()
	{
		int end = game_obj_.Answer().indexOf(guess_);
		if(end == LETTER_NOT_FOUND)
		{
			System.out.println(guess_ + " not found. Sorry :(");
		}
		else
		{
			int begin = 0;
			while(end != LETTER_NOT_FOUND)
			{
				System.out.println("Nice! " + guess_ + " was found on index: " + end);
				
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
			guess_  = console_.readLine("Guess the word or enter a letter: ");
		}
		while(!ValidatePrompt(guess_));
	}
	
	//if we found a digit or a non-word character
	// the imput from the user is invalid
	private boolean ValidatePrompt(String prompt)
	{
		if(prompt.isEmpty()) return false;
		else
		{
			Pattern pattern = Pattern.compile("[^a-zA-Z]");
			Matcher matcher = pattern.matcher(prompt);

			if(matcher.find()) return false;
			else               return true;
		}
	}

	private Console console_;
	private Game game_obj_;
	private String guess_;
	private final int LETTER_NOT_FOUND = -1;
}
