import gr.kourtzis.*;

public class Hangman
{
	public static void 
	main(String argv[])
	{
		Game hangman = new Game("example");
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
