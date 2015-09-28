import gr.kourtzis.Game;
import gr.kourtzis.Prompter;
import java.util.Scanner;

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

		boolean easy_mode = false;
		if(argv.length > 0) easy_mode = ChooseMode(argv[0]);

		try(Scanner scanner = new Scanner(System.in))
		{
			Game hangman      = new Game(list_of_words, easy_mode);
			Prompter prompter = new Prompter(hangman, scanner);

			prompter.Play();
		}
		catch(Exception ex) { ErrorHandler(ex); }
	}

	private static boolean ChooseMode(String mode) { return !mode.isEmpty() && (mode.equalsIgnoreCase("easy")); }

	private static void ErrorHandler(Exception ex)
	{
		System.err.println("An exception occurred: " + ex.getMessage());
		ex.printStackTrace();
	}
}
