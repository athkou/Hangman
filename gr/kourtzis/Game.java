package gr.kourtzis;

public class Game
{
	public Game(String answer)
	{	
		answer_ = answer;
		remaining_tries_ = MAX_TRIES;
	}

	public String Answer()                    { return answer_;                  }
	public boolean GuessLetter(String letter) { return answer_.contains(letter); }
	public String Hits()                      { return hit_;                     }
	public String Misses()                    { return miss_;                    }
	public int RemainingTries()               { return remaining_tries_;         }

	public boolean GameOver()                 { return remaining_tries_ == GAME_OVER; }
	public void GoodGuess(char letter)        { hit_ += letter;                       }
	public void WrongGuess(char letter)
	{
		miss_ += letter;
		--remaining_tries_;
	}

	private String answer_;

	private String hit_  = "";
	private String miss_ = "";
	private int remaining_tries_;

	private final int MAX_TRIES = 7;
	private final int GAME_OVER = 0;
}
