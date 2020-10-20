package ehu.isad.db;

import ehu.isad.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LiburuKud {

    private static final LiburuKud instance = new LiburuKud();

    public static LiburuKud getInstance() {
        return instance;
    }

    private LiburuKud() {
    }

    public ObservableList liburuenListaBete(){
        String query = "select ISBN,izena from Liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        ObservableList<Book> lista = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String izena = rs.getString("izena");
                lista.add(new Book(isbn,izena));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return lista;
    }

    public boolean liburuaDago(String ISBN){
        String query = "select count(*) from Liburua where ISBN = '"+ISBN+"' and orriKop > 0";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        int count = 0;
        try {
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        if(count==0){
            return false;
        }else{
            return true;
        }
    }

    public void liburuarenDatuSartuDB(Book b,String isbn){
        liburuarenDatuakEguneratu(b,isbn);
        argitaletxeaSartu(b);
    }

    private void liburuarenDatuakEguneratu(Book b, String isbn){
        String query = "update Liburua set orriKop ="+b.getDetails().getNumber_of_pages()+" where ISBN = '"+isbn+"'";
        System.out.println(query);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }

    private boolean argialetxeaDago(String izena){
        String query = "select count(*) from Argitaletxea where izena = '"+izena+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        int count = 0;
        try {
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        if(count==0){
            return false;
        }else{
            return true;
        }
    }

    private void argitaletxeaSartu(Book b){
        int zenbat = b.getDetails().getPublishers().length;
        String argita[] = b.getDetails().getPublishers();
        int i = 0;
        while(i < zenbat){
            /*if(!argialetxeaDago(argita[i])){
                String query = "insert into Argitaletxea values('"+argita[i]+"')";
                DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
                ResultSet rs = dbKudeatzaile.execSQL(query);
            }*/
           // liburuArgitaErlazioa(b.getIsbn(),argita[i]);
            i++;
        }
    }

    private void liburuArgitaErlazioa(String ISBN,String izena){
        String query = "Insert into Eduki values('"+ISBN+"','"+izena+"')";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }

    public List liburuenDatuenListaLortu(String isbn){
        String query = "select izena,orriKop from Liburua where ISBN ='"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        List<String> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                int orriKop = rs.getInt("orriKop");
                lista.add(izena);
                lista.add(Integer.toString(orriKop));
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return lista;
    }

}
