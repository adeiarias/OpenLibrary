package ehu.isad.controllers.db;

import ehu.isad.Model.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDB {

    private static MessageDB instantzia = new MessageDB();
    public static MessageDB getInstantzia(){
        return instantzia;
    };
    private MessageDB(){}

    public ObservableList<Message> hasieratuPanela(){
        ObservableList<Message> lista = FXCollections.observableArrayList();
        String query = "select * from DirectMessage";
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbkud.execSQL(query);
        int id;
        String from,to,message;
        try{
            while(rs.next()){
                id = rs.getInt("id");
                from = rs.getString("fromUser");
                to = rs.getString("toUser");
                message = rs.getString("message");
                lista.add(new Message(id,from,to,message));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public void deleteDatabase(){
        String query = "delete from DirectMessage";
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
        dbkud.execSQL(query);
    }

    public void insertInto(Message mg){
        String query = "insert into DirectMessage values("+mg.getId()+",'"+mg.getFrom()+"','"+mg.getTo()+"','"+mg.getMessage()+"')";
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
        dbkud.execSQL(query);
    }
}
