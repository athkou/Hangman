import gr.kourtzis.*;

public class Hangman
{
	public static void 
	main(String argv[])
	{
		String list[] =
		{
			"hangman", "treehouse", "development",
			"interface", "rhythm", "zephyr",
			"equilibrium", "quake", "jazz",
			"babe", "swan", "trumpet",
			"judo", "soccer", "physics"
		};

		Game hangman = new Game(list);
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
