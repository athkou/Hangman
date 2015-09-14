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
		Configure();
	}

	public void Play()
	{
		while(!game_obj_.GameOver() || !game_obj_.IsSolved(masked_answer_))
		{
			//Test();
			Display();
			PromptForGuess();
			CheckGuess();
		}
	}

	public void Display()
	{
		System.out.print("Word to guess: ");
		System.out.println(masked_answer_);
		System.out.println("Remaining tries: " + game_obj_.RemainingTries());
		System.out.print("Letters used: ");
		
		char temp[] = game_obj_.Letters().toCharArray();
		for(int it : temp) System.out.print(temp[it] + ", "); 
		System.out.println();

		//PromptForGuess();
		//System.out.println("You guessed: " + guess_);
		//CheckGuess();
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
		if(condition != LETTER_NOT_FOUND)
		{
			Update(condition);
		}
	}

	private void CheckGuess()
	{
		int end = game_obj_.Answer().indexOf(guess_);
		if(end == LETTER_NOT_FOUND) 
		{
			Update(end, "Did not found it. Sorry :(");
			game_obj_.Miss();
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
			guess_  = console_.readLine("Guess the word or enter a letter: ");
		}
		while(!ValidatePrompt(guess_));
	}
	
	//if we found a digit or a non-word character
	// the imput from the user is invalid
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

			if(matcher.find()) return false;
			else               return true;
		}
	}

	private void Test()
	{
		/*
		String temp = "example";
		String tmp = "";
		
		for(char ch : temp.toCharArray())
			tmp += "_";
		System.out.println("Guessed so far: " + tmp);
		System.out.println("Enter a letter: ");
		*/
	}
	private void Configure()
	{
		for(char tmp : game_obj_.Answer().toCharArray()) masked_answer_ += "_";
	}

	private Console console_;
	private Game game_obj_;
	private String guess_              = "";
	private String masked_answer_      = "";
	private final int LETTER_NOT_FOUND = -1;
	private final int ONE_LETTER       = 1;
}
