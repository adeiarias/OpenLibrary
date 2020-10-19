package ehu.isad.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LiburukuKud {

    private static final LiburukuKud instance = new LiburukuKud();

    public static LiburukuKud getInstance() {
        return instance;
    }

    private LiburukuKud() {
    }

    public List<String> lortuZerbitzuak() {

        String query = "select id, izena from services";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                int kodea = rs.getInt("id");
                String izena = rs.getString("izena");
                System.out.println("kodea " + kodea);
                System.out.println(kodea + ":" + izena);
                emaitza.add(izena);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void kenduIzena(String iz){
        String query = "delete from services where izena ='"+iz+"';";
        System.out.println(query);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }

    public boolean gehituBeharBada(String iz){
        boolean em = false;
        String query = "select id from services where izena= '"+iz+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        if(rs == null){
            sartuDatuBasean(iz);
            em = true;
        }
        return em;
    }

    public void sartuDatuBasean(String iz){
        String query = "insert into services values('"+iz+"')";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

    }
}
