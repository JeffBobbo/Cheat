package question2;

public interface CardGame
{
  /**
   * Initialises the card game
   */
  public void initialise();

  /**
   * Plays a single turn of the game
   * @return True if a play was made
   */
  public boolean playTurn();

  /**
   * Determines if there's a winner
   * @return The id of the winner, starting from 0.
   */
  public int winner();
}
