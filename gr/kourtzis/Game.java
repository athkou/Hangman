package gr.kourtzis;

public class Game
{
	public Game(String answer)
	{	
		answer_ = answer;
	}

	public String Answer() { return answer_; }

	public boolean GuessLetter(String letter)
	{
		return answer_.contains(letter);
	}

	private String answer_;

	private String hit_  = "";
	private String miss_ = ""; 
}
