package game;

/**
 * Custom Game Exception
 * Subclass of the class Exception
 */
public class GameException extends Exception {
	
	/**
	 * Constructor for GameException
	 * Calls the superclass Exception
	 * @param message
	 */
    public GameException(String message) {
        super(message);
    }
}