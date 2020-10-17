package ehu.isad.utils;


import com.google.gson.Gson;
import ehu.isad.Book;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {

    private static Sarea nireSarea = null;

    private Sarea(){}

    public static Sarea getNireSarea(){
        if(nireSarea==null){
            nireSarea = new Sarea();
        }
        return nireSarea;
    }

    public Book liburuarenDatuakHasieratu(String ISBN) throws IOException {
        URL openlibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+ISBN+"&jscmd=details&format=json");
        URLConnection yc = openlibrary.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine();
        in.close();

        String[] zatiak = inputLine.split("ISBN:"+ISBN+"\":");
        String emaitza = zatiak[1].substring(1, zatiak[1].length()-1);//LEHENENGO HUTSUNEA ETA AZKENEKO GILTZA KENTZEKO
        Gson gson = new Gson();
        return gson.fromJson(emaitza, Book.class);
    }

    public Image createImage(String url) throws IOException {
        String urlBerria = url.replace("-S.","-M.");
        URLConnection conn = new URL(urlBerria).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

}
