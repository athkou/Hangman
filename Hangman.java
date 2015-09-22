import gr.kourtzis.Game;
import gr.kourtzis.Prompter;
import org.xml.sax.ErrorHandler;

import java.io.IOException;
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



		try(Scanner scanner = new Scanner(System.in))
		{

			Game hangman      = new Game(list_of_words);
			Prompter prompter = new Prompter(hangman, scanner);

			prompter.Play();
		} catch(Exception ex)
		{
			ErrorHandler(ex);
		}
	}

	private static void ErrorHandler(Exception ex)
	{
		System.out.println("An exception occurred: " + ex.getMessage());
		ex.printStackTrace();
	}

}
