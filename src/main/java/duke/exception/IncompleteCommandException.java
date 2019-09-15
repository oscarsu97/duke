package duke.exception;

/**
 * Exception class that handles incomplete user's command input.
 */
public class IncompleteCommandException extends DukeException {

    public IncompleteCommandException(String msg) {
        super(msg);
    }

}