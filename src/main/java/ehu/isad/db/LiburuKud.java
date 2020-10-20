package ehu.isad.db;

import ehu.isad.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
