package Duke.DukeException;

public class IndexOffBoundException extends Exception {

    @Override
    public String toString(){
        return "☹ OOPS!!! " + "please enter a valid index";
    }
}