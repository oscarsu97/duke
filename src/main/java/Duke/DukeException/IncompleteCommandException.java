package Duke.DukeException;

/**
 * Exception class that deals with incomplete user's command input.
 */
public class IncompleteCommandException extends Exception{
    public String msg;

    /**
     * initialise Exception class with a string message.
     * @param msg message to send to user
     */
    public IncompleteCommandException(String msg){
        this.msg = msg;
    }

    /**
     * Return a string representation of the class.
     * @return String representation
     */
    @Override
    public String toString(){
        return "☹ OOPS!!! " + msg;
    }
}