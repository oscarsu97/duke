package Duke.DukeException;

public class IncompleteCommandException extends Exception{
    public String msg;

    public IncompleteCommandException(String msg){
        this.msg = msg;
    }

    @Override
    public String toString(){
        return "☹ OOPS!!! " + msg;
    }
}