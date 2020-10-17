package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburua;
import ehu.isad.utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {

    private Liburua mainApp;
    private Sarea sarea;

    @FXML
    private Label lblIzenburu;

    @FXML
    private Label lblArgitaletxe;

    @FXML
    private Label lblOrriKop;

    @FXML
    private Label lblIrudi;

    @FXML
    private Label izenburua;

    @FXML
    private Label argitaletxea;

    @FXML
    private Label orriKop;

    @FXML
    private ImageView irudia;

    @FXML
    private Button buttonAtzera;

    public void setMainApp(Liburua main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent event) {
        mainApp.liburuakErakutsi();
    }

    private void labelakEguneratu(Book book) throws IOException {
        String izen = book.getDetails().getTitle();
        String orri= book.getDetails().getNumber_of_pages();
        String[] argita = book.getDetails().getPublishers();

        izenburua.setText(izen);
        orriKop.setText(orri);
        argitaletxeakEguneratu(argita);

        Image irudi = sarea.createImage(book.getThumbnail_url());
        irudia.setImage(irudi);
    }

    private void argitaletxeakEguneratu(String[] argitaletxeZerr){
        String[] argitaletxe = argitaletxeZerr;
        String argi = null;
        for(int i = 0; i < argitaletxe.length; i++){
            if(argi == null){
                argi = argitaletxe[i];
            }else{
                argi = argi +", "+argitaletxe[i];
            }
        }
        argitaletxea.setText(argi);
    }

    public void datuakErakutsi(Book liburua) throws IOException {
        Book book = sarea.liburuarenDatuakHasieratu(liburua.getIsbn());
        labelakEguneratu(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sarea = Sarea.getNireSarea();
    }
}