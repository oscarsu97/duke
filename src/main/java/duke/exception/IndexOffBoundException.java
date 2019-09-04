package duke.exception;

/**
 * Exception class that deals with wrong index given by user
 * that is out of the size of the task list.
 */
public class IndexOffBoundException extends Exception {

    /**
     * Return a string representation of the class.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + "please enter a valid index";
    }
}