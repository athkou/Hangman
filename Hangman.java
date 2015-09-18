import gr.kourtzis.*;

/**
 * Hangman class. The main starting point of the application.
 * Declares the game and prompter objects and has hardcoded a list of words to be used.
 */
public class Hangman
{
	public static void 
	main(String argv[])
	{
		String list_of_words[] =
		{
			"hangman", "treehouse", "development",
			"interface", "rhythm", "zephyr",
			"equilibrium", "quake", "jazz",
			"babe", "swan", "trumpet",
			"judo", "soccer", "physics",
			"wheel", "stalemate", "fortune",
			"mystery", "magician", "wizard"
		};

		Game hangman      = new Game(list_of_words);
		Prompter prompter = new Prompter(hangman);

		try
		{
			prompter.Play();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
