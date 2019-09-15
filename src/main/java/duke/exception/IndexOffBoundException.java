package duke.exception;

/**
 * Exception class that handles wrong index given by user
 * that is out of the size of the task list.
 */
public class IndexOffBoundException extends DukeException {

    public IndexOffBoundException(String msg) {
        super(msg + ",please enter a valid index");
    }
}