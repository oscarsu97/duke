public class InvalidCommandException extends Exception {

    public String msg;

    public InvalidCommandException(String msg){
        this.msg = msg;
    }

    @Override
    public String toString(){
        return "☹ OOPS!!! " + msg;
    }
}
