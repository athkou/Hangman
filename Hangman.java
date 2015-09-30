import gr.kourtzis.Game;
import gr.kourtzis.Prompter;
import gr.kourtzis.SQLiteDB;

import java.util.Scanner;

import java.sql.*;

/**
 * Hangman class. The main starting point of the application.
 * Declares the game and prompter objects and has hardcoded a list of words to be used.
 */
public class Hangman
{
	public static void 
	main(String argv[])
	{
		TestDB();
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

		/*
		boolean easy_mode = false;
		if(argv.length > 0) easy_mode = ChooseMode(argv[0]);

		try(Scanner scanner = new Scanner(System.in))
		{
			Game hangman      = new Game(list_of_words, easy_mode);
			Prompter prompter = new Prompter(hangman, scanner);

			prompter.Play();
		}
		catch(Exception ex) { ErrorHandler(ex); }
		*/

	}

	private static void TestDB()
	{
		try
		{
			SQLiteDB my_db = new SQLiteDB();
			Statement create_table = null;

			String statement = "DELETE FROM LANGUAGE";
			my_db.Execute(create_table, statement);

			statement = "CREATE TABLE IF NOT EXISTS LANGUAGE " +
					           "(ID   INT PRIMARY KEY NOT NULL," +
					           " DESC VARCHAR(25))";
			my_db.Execute(create_table, statement);

			statement = "INSERT INTO LANGUAGE(ID, DESC) " +
			            "VALUES (1, \"Greek\")," +
						"(2, \"English\")," +
						"(3, \"German\")";
			my_db.Execute(create_table, statement);

			statement = "SELECT * FROM LANGUAGE";

			my_db.Execute(create_table, statement);
			System.out.println("Inside main()");
			while(my_db.Result().next())
			{
				int id = my_db.Result().getInt("ID");
				String des = my_db.Result().getString("DESC");

				System.out.println("ID: " + id);
				System.out.println("DESC: " + des);
			}
		}
		catch(SQLException ex)
		{
			System.err.println("Exception: " + ex.getMessage());
		}

		System.out.println("Opened database and created table successfully");
	}

	private static boolean ChooseMode(String mode) { return !mode.isEmpty() && (mode.equalsIgnoreCase("easy")); }

	private static void ErrorHandler(Exception ex)
	{
		System.err.println("An exception occurred: " + ex.getMessage());
		ex.printStackTrace();
	}
}
