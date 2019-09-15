package duke.exception;

/**
 * Exception class that handles with invalid command given by user
 * which the bot cannot recognise.
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String msg) {
        super(msg);
    }
}
