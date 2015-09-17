import gr.kourtzis.*;

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
