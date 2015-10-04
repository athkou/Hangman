import gr.kourtzis.Game;
import gr.kourtzis.Prompter;
import gr.kourtzis.SQLiteDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hangman
{
	public static void 
	main(String argv[])
	{
		InitDB();
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

	}

	private static void InitDB()
	{
		try
		{
			SQLiteDB my_db = new SQLiteDB();
			Statement stmt = null;

			String sql_op = "DELETE FROM WORD_IN_CAT";
			my_db.Execute(stmt, sql_op);
			sql_op = "DELETE FROM WORDS";
			my_db.Execute(stmt, sql_op);
			sql_op = "DELETE FROM CATEGORY";
			my_db.Execute(stmt, sql_op);
			sql_op = "DELETE FROM LANGUAGE";
			my_db.Execute(stmt, sql_op);
			System.out.println("DELETE command ok");

			sql_op = "DROP TABLE IF EXISTS WORD_IN_CAT";
			my_db.Execute(stmt, sql_op);
			sql_op = "DROP TABLE IF EXISTS WORDS";
			my_db.Execute(stmt, sql_op);
			sql_op = "DROP TABLE IF EXISTS CATEGORY";
			my_db.Execute(stmt, sql_op);
			sql_op = "DROP TABLE IF EXISTS LANGUAGE";
			my_db.Execute(stmt, sql_op);
			System.out.println("DROP command ok");

			sql_op = "CREATE TABLE IF NOT EXISTS LANGUAGE " +
					"(LANG_ID INT PRIMARY KEY ASC," +
					" DESC VARCHAR(25))";
			my_db.Execute(stmt, sql_op);

			sql_op = "CREATE TABLE IF NOT EXISTS CATEGORY " +
					"(CAT_ID INT PRIMARY KEY ASC," +
					" DESC VARCHAR(200)," +
					"L_ID INT," +
					" FOREIGN KEY(L_ID) REFERENCES LANGUAGE(LANG_ID))";
			my_db.Execute(stmt, sql_op);

			sql_op = "CREATE TABLE IF NOT EXISTS WORDS " +
					"(WORD_ID INT PRIMARY KEY ASC," +
					" WORD VARCHAR(200)," +
					"L_ID INT," +
					" FOREIGN KEY(L_ID) REFERENCES LANGUAGE (LANG_ID))";
			my_db.Execute(stmt, sql_op);

			sql_op = "CREATE TABLE IF NOT EXISTS WORD_IN_CAT " +
					 "(ID INT PRIMARY KEY ASC," +
					 " W_ID INT," +
					 " C_ID INT," +
					 " FOREIGN KEY(W_ID) REFERENCES WORDS (WORD_ID)," +
					 " FOREIGN KEY(C_ID) REFERENCES CATEGORY(CAT_ID))";
			my_db.Execute(stmt, sql_op);

			System.out.println("CREATE TABLE command ok");

			sql_op = "INSERT INTO LANGUAGE(LANG_ID, DESC) " +
					"VALUES (1, \"Greek\")," +
					"(2, \"English\")," +
					"(3, \"German\")";
			my_db.Execute(stmt, sql_op);


			sql_op = "INSERT INTO CATEGORY(CAT_ID, DESC, L_ID) " +
					"VALUES (0, \"misc\", 2)," +
					"(1, \"literature\", 2)," +
					"(2, \"sport\", 2)," +
					"(3, \"movies\", 2)";
			my_db.Execute(stmt, sql_op);

			sql_op = "INSERT INTO WORDS(WORD_ID, WORD, L_ID) " +
					"VALUES (1, \"literature\", 2)," +
					"(2, \"written\", 2)," +
					"(3, \"spoken\", 2)," +
			        "(4, \"horror\", 2)";
			my_db.Execute(stmt, sql_op);

			sql_op = "INSERT INTO WORD_IN_CAT(ID, W_ID, C_ID) " +
					"VALUES (0, 1, 1)," +
					"(1, 2, 1)," +
					"(2, 3, 1)," +
			        "(3, 4, 3)";
			my_db.Execute(stmt, sql_op);

			System.out.println("INSERT command ok");

			sql_op = "SELECT WORD FROM WORDS, WORD_IN_CAT " +
					 "WHERE WORD_ID = W_ID " +
					 "AND L_ID = 2 " +
					 "AND C_ID = 1";
			my_db.Execute(stmt, sql_op);
			while(my_db.Result().next())
			{
				String word = my_db.Result().getString("WORD");
				System.out.print("Word: " + word + '\n');
			}
		}
		catch(SQLException ex){ System.err.println("Exception: " + ex.getMessage()); }

	}

	private static boolean ChooseMode(String mode) { return !mode.isEmpty() && (mode.equalsIgnoreCase("easy")); }

	private static void ErrorHandler(Exception ex)
	{
		System.err.println("An exception occurred: " + ex.getMessage());
		ex.printStackTrace();
	}
}
