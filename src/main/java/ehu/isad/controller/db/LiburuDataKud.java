package ehu.isad.controller.db;

import ehu.isad.Book;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LiburuDataKud {

    private static final LiburuDataKud instance = new LiburuDataKud();

    public static LiburuDataKud getInstance() {
        return instance;
    }

    private LiburuDataKud() {
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




    public void liburuarenDatuSartuDB(Book b, String isbn) throws IOException {
        liburuarenDatuakEguneratu(b,isbn);
        argitaletxeaSartu(b,isbn);
        irudiaSartu(b.getThumbnail_url().replace("-S.","-M."),isbn);
    }

    private void liburuarenDatuakEguneratu(Book b, String isbn){
        String query = "update Liburua set orriKop ="+b.getDetails().getNumber_of_pages()+", irudiIzena = '"+isbn+".png' where ISBN = '"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }

    private void argitaletxeaSartu(Book b,String isbn){
        int zenbat = b.getDetails().getPublishers().length;
        String argita[] = b.getDetails().getPublishers();
        int i = 0;
        while(i < zenbat){
            if(!argialetxeaDago(argita[i])){
                String query = "insert into Argitaletxea values(\""+argita[i]+"\")";
                DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
                ResultSet rs = dbKudeatzaile.execSQL(query);
            }
            liburuArgitaErlazioa(isbn,argita[i]);
            i++;
        }
    }

    private boolean argialetxeaDago(String izena){
        String query = "select count(*) from Argitaletxea where izena = \""+izena+"\"";
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

    //metodo hau argitaletxe eta liburuaren arteko erlazioaren taula izango da
    private void liburuArgitaErlazioa(String ISBN,String izena){
        String query = "Insert into Eduki values('"+ISBN+"',\""+izena+"\")";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }

    private void irudiaSartu(String url, String isbn) throws IOException {
        BufferedImage irudia = ImageIO.read(new URL(url));
        String path = isbn+".png";
        Properties properties = Utils.lortuEzarpenak();
        String imagePath = properties.getProperty("imagePath");
        Files.createDirectories(Paths.get(imagePath));//setup.propertien jarri dugun karpeta sortuko dugu
        File file = new File(imagePath+"/"+path);
        ImageIO.write(irudia,"png",file);
    }




    public List liburuenDatuenListaLortu(String isbn){
        String query = "select izena,orriKop,irudiIzena from Liburua where ISBN ='"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        List<String> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                int orriKop = rs.getInt("orriKop");
                String irudiIzena = rs.getString("irudiIzena");
                lista.add(izena);
                lista.add(Integer.toString(orriKop));
                lista.add(irudiIzena);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return lista;
    }

    public List argitaletxeenZerrenda(String isbn){
        String query = "select izena from Eduki where ISBN = '"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        List<String> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                lista.add(izena);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return lista;
    }

}
