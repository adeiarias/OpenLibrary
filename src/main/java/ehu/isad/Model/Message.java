package ehu.isad.Model;

public class Message {

    private int id;
    private String from;
    private String to;
    private String message;

    public Message(int i, String f, String t, String m){
        id = i;
        from = f;
        to = t;
        message = m;
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
