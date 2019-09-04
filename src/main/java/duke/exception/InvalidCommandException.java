package duke.exception;

/**
 * Exception class that deals with invalid command given by user.
 */
public class InvalidCommandException extends Exception {

    public String msg;

    /**
     * initialise Exception class with a string message.
     *
     * @param msg message to send to user
     */
    public InvalidCommandException(String msg) {
        this.msg = msg;
    }

    /**
     * Return a string representation of the class.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + msg;
    }
}
