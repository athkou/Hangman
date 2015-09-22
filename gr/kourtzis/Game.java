package gr.kourtzis;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


/**
 * The class game. It chooses a word to be guessed and
 * provides helper methods to aid the prompter object
 */
public class Game {

	private String mAnswer;			/**< The string variable that contains the word that has to be found*/
	private boolean mFound;			/**< A boolean variable which indicates if the user was able to found the word*/
	private String mLetters;		/**< A string variable that contains all the letters used to find the word*/
	private int mRemainingTries;	/**< An integer with the tries the user has left to found the word*/
	private List<String> mWords;	/**< A list of words*/

	private final int DEFAULT_TRIES    = 7;
	private final int GAME_OVER        = 0;


	/**
	 * The constructor of the class. It initializes the member variables
	 * @param answer A string variable. Contains the word to be guessed.
	 */
	public Game(String answer)
	{	
		mAnswer = answer;
		Init();
	}


	/**
	 * The constructor of the class. It initializes the member variables
	 * @param answers An array of strings. Contains the words to be guessed.
	 */
	public Game(String answers[])
	{
		PopulateList(answers);
		ChooseRandomWord();
		Init();
	}


	/**
	 * The public method Answer. Accessory method to return
	 * the value of the variable mAnswer
	 * @return The string of the member variable mAnswer.
	 */
	public String Answer()                       { return mAnswer;                                }


	/**
	 * The public method Found. Accessory method to return
	 * the value of the variable mFound
	 * @return true or false
	 */
	public boolean Found()                       { return mFound;                                 }


	/**
	 * The public method GuessLetter.
	 * @param letter the letter the user picked up as a guess
	 * @return it return true or false, whether the word contains the letter
	 */
	public boolean GuessLetter(String letter)    { return mAnswer.contains(letter);               }


	/**
	 * The public method Letters. Accessory method to return
	 * the value of the variable mLetters
	 * @return The string of the member variable mLetters.
	 */
	public String Letters()                      { return mLetters;                               }


	/**
	 * The public method RemainingTries. Accessory method to return
	 * the value of the variable mRemainingTries.
	 * @return The integer of the member variable mRemainingTries.
	 */
	public int RemainingTries()                  { return mRemainingTries;                       }


	/**
	 * The public method IsSolved. It checks if the user was able to solve the problem.
	 * @param guess_so_far A string variable containing the guesses from the user
	 * @return true or false
	 */
	public boolean IsSolved(String guess_so_far) { return mAnswer.equalsIgnoreCase(guess_so_far); }


	/**
	 * The public method GameOver. It checks if the game is over.
	 * @return true or false
	 */
	public boolean GameOver()                    { return mRemainingTries == GAME_OVER;          }


	/**
	 * The public method ChangeState. It changes the value of the member variable mFound
	 * @param new_state a boolean variable.
	 */
	public void ChangeState(boolean new_state)   { mFound = new_state;                            }


	/**
	 * The public method Miss. Each time the user makes a wrong guess
	 * his tries are being decreased.
	 */
	public void Miss()                           { --mRemainingTries;                            }


	/**
	 * The public method Guess. Calls the method GuessString and
	 * under some conditions it populates the variable mLetters
	 * @param letter A string variable that contains the input from the user
	 */
	public void Guess(String letter)
	{
		// wrong letter and did not use it before
		if(!GuessLetter(letter) && !mLetters.contains(letter))
		{
			Miss();
			mLetters += letter;
		}

		// Right letter. Checking if the word contains more than once the letter
		if(!mLetters.contains(letter)) mLetters += letter;

	}


	/**
	 * The private method PopulateList. It fills the list with words.
	 * @param answers A string array that contains words.
	 */
	private void PopulateList(String answers[])
	{
		mWords = new ArrayList<>();
        Collections.addAll(mWords, answers);
	}


	/**
	 * The private method ChooseRandomWord. It chooses randomly a word from the list to be guessed.
	 */
	private void ChooseRandomWord()
	{
		Random generator = new Random();
		int index        = generator.nextInt(mWords.size() - 1);
		mAnswer = mWords.get(index);
	}


	/**
	 * The private method Init. It initializes its member variables with values.
	 */
	private void Init()
	{
		mFound = false;
		mRemainingTries = DEFAULT_TRIES;
		mLetters = "";
	}
}
