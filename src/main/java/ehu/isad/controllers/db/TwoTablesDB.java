package ehu.isad.controllers.db;

import ehu.isad.Model.TwoTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TwoTablesDB {

    private DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
    private static TwoTablesDB instantzia = new TwoTablesDB();
    public static TwoTablesDB getInstantzia(){
        return instantzia;
    };
    private TwoTablesDB(){}

    public ObservableList<TwoTables> fillFirstTable(){
        ObservableList<TwoTables> lista = FXCollections.observableArrayList();
        String query = "select * from datuak";
        ResultSet rs = dbKudeatzaile.execSQL(query);
        String first,last,sport,years;
        try{
            while (rs.next()) {
                first = rs.getString("firstname");
                last = rs.getString("lastname");
                sport = rs.getString("sport");
                years = Integer.toString(rs.getInt("numyears"));
                lista.add(new TwoTables(first,last,sport,years,"hola","adios"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
    }

    public void deleteFromDatabase(){
        String q = "delete from datuak";
        dbKudeatzaile.execSQL(q);
    }

    public void insertInto(TwoTables tt){
        String q = "insert into datuak values('"+tt.getFirstName()+"','"+tt.getLastName()+"','"+tt.getSport()+"','"+tt.getYears()+"','"+tt.getVegetarian()+"','"+tt.getIrudi()+"')";
        System.out.println(q);
        dbKudeatzaile.execSQL(q);
    }
}
