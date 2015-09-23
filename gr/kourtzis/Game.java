package gr.kourtzis;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * The class game. It chooses a word to be guessed and
 * provides helper methods to aid the prompter object
 */
public class Game
{
	/**
	 * The constructor of the class. It initializes the member variables
	 * @param answer A string variable. Contains the word to be guessed.
	 */
	public Game(String answer)
	{	
		answer_ = answer;
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
	 * the value of the variable answer_
	 * @return The string of the member variable answer_.
	 */
	public String Answer()                       { return answer_;                                }

	/**
	 * The public method Found. Accessory method to return
	 * the value of the variable found_
	 * @return true or false
	 */
	public boolean Found()                       { return found_;                                 }

	/**
	 * The public method GuessLetter.
	 * @param letter the letter the user picked up as a guess
	 * @return it return true or false, whether the word contains the letter
	 */
	public boolean GuessLetter(String letter)    { return answer_.contains(letter);               }

	/**
	 * The public method Letters. Accessory method to return
	 * the value of the variable letters_
	 * @return The string of the member variable letters_.
	 */
	public String Letters()                      { return letters_;                               }

	/**
	 * The public method RemainingTries. Accessory method to return
	 * the value of the variable remaining_tries_.
	 * @return The integer of the member variable remaining_tries_.
	 */
	public int RemainingTries()                  { return remaining_tries_;                       }

	/**
	 * The public method IsSolved. It checks if the user was able to solve the problem.
	 * @param guess_so_far A string variable containing the guesses from the user
	 * @return true or false
	 */
	public boolean IsSolved(String guess_so_far) { return answer_.equalsIgnoreCase(guess_so_far); }

	/**
	 * The public method GameOver. It checks if the game is over.
	 * @return true or false
	 */
	public boolean GameOver()                    { return remaining_tries_ == GAME_OVER;          }

	/**
	 * The public method ChangeState. It changes the value of the member variable found_
	 * @param new_state a boolean variable.
	 */
	public void ChangeState(boolean new_state)   { found_ = new_state;                            }

	/**
	 * The public method Miss. Each time the user makes a wrong guess
	 * his tries are being decreased.
	 */
	public void Miss()                           { --remaining_tries_;                            }

	/**
	 * The public method Guess. Calls the method GuessString and
	 * under some conditions it populates the variable letters_
	 * @param letter A string variable that contains the input from the user
	 */
	public void Guess(String letter)
	{
		// wrong letter and did not use it before
		if(!GuessLetter(letter) && !letters_.contains(letter))
		{
			Miss();
			letters_ += letter;
		}

		// Right letter. Checking if the word contains more than once the letter
		if(!letters_.contains(letter)) letters_ += letter;

	}

	/**
	 * The private method PopulateList. It fills the list with words.
	 * @param answers A string array that contains words.
	 */
	private void PopulateList(String answers[])
	{
		words_ = new ArrayList<>();
		Collections.addAll(words_, answers);
	}

	/**
	 * The private method ChooseRandomWord. It chooses randomly a word from the list to be guessed.
	 */
	private void ChooseRandomWord()
	{
		Random generator = new Random();
		int index        = generator.nextInt(words_.size() - 1);
		answer_          = words_.get(index);
	}

	/**
	 * The private method Init. It initializes its member variables with values.
	 */
	private void Init()
	{
		found_           = false;
		remaining_tries_ = DEFAULT_TRIES;
		letters_         = "";
	}

	private String answer_;			/**< The string variable that contains the word that has to be found*/
	private boolean found_;			/**< A boolean variable which indicates if the user was able to found the word*/
	private String letters_;		/**< A string variable that contains all the letters used to find the word*/
	private int remaining_tries_;	/**< An integer with the tries the user has left to found the word*/
	private List<String> words_;	/**< A list of words*/

	private final int DEFAULT_TRIES    = 7;
	private final int GAME_OVER        = 0;
}
