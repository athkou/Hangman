package gr.kourtzis;

public class Game
{
	public Game(String answer)
	{	
		answer_ = answer;
		found_ = false;
		remaining_tries_ = MAX_TRIES;
	}

	public String Answer()                       { return answer_;                                }
	public boolean Found()                       { return found_;                                 }
	public boolean GuessLetter(String letter)    { return answer_.contains(letter);               }
	public String Letters()                      { return letters_;                               }
	public int RemainingTries()                  { return remaining_tries_;                       }

	public boolean IsSolved(String guess_so_far) { return answer_.equalsIgnoreCase(guess_so_far); }
	public boolean GameOver()                    { return remaining_tries_ == GAME_OVER;          }
	
	public void ChangeState(boolean new_state)   { found_ = new_state;                            }
	public void Miss()
	{
		 if(!GameOver()) --remaining_tries_;
	}
	public void Guess(String letter)
	{
		if(!GuessLetter(letter)) Miss();
 		letters_ += letter;
	}

	private String answer_;
	private boolean found_;
	private String letters_  = "";
	private int remaining_tries_;

	private final int MAX_TRIES = 7;
	private final int GAME_OVER = 0;
}
