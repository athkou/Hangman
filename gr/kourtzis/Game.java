package gr.kourtzis;

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

	public Game(String answers[])
	{
		PopulateList(answers);
		ChooseRandomWord();
		Init();
	}

	public String Answer()                       { return answer_;                                }
	public boolean Found()                       { return found_;                                 }
	public boolean GuessLetter(String letter)    { return answer_.contains(letter);               }
	public String Letters()                      { return letters_;                               }
	public int RemainingTries()                  { return remaining_tries_;                       }

	public boolean IsSolved(String guess_so_far) { return answer_.equalsIgnoreCase(guess_so_far); }
	public boolean GameOver()                    { return remaining_tries_ == GAME_OVER;          }
	
	public void ChangeState(boolean new_state)   { found_ = new_state;                            }
	public void Miss()                           { --remaining_tries_;                            }
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

	private void PopulateList(String answers[])
	{
		words_ = new ArrayList<>();
		for(String temp : answers) words_.add(temp);
	}

	private void ChooseRandomWord()
	{
		Random generator = new Random();
		int index        = generator.nextInt(words_.size() - 1);
		answer_          = words_.get(index);
	}

	private void Init()
	{
		found_           = false;
		remaining_tries_ = DEFAULT_TRIES;
		letters_         = "";
	}

	private String answer_;
	private boolean found_;
	private String letters_;
	private int remaining_tries_;
	private List<String> words_;

	private final int DEFAULT_TRIES    = 7;
	private final int GAME_OVER        = 0;
}
