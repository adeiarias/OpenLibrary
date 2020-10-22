package ehu.isad.controller.ui;

import ehu.isad.Book;
import ehu.isad.Liburua;
import ehu.isad.controller.db.LiburuDataKud;
import ehu.isad.utils.Sarea;
import ehu.isad.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    private void labelakEguneratu(List<String> lista) throws IOException {
        String izen = lista.get(0);
        String orri= lista.get(1);
        izenburua.setText(izen);
        orriKop.setText(orri);

        String irudiIzen = lista.get(2);
        String imagePath = Utils.lortuEzarpenak().getProperty("imagePath")+"/"+irudiIzen;
        try {
            irudia.setImage(new Image(new FileInputStream(imagePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void argitaletxeakEguneratu(List<String> argitaletxeZerr){
        String argi = null;
        for(int i = 0; i < argitaletxeZerr.size(); i++){
            if(argi == null){
                argi = argitaletxeZerr.get(i);
            }else{
                argi = argi +", "+argitaletxeZerr.get(i);
            }
        }
        argitaletxea.setText(argi);
    }

    public void datuakErakutsi(Book liburua) throws IOException {
        if(!LiburuDataKud.getInstance().liburuaDago(liburua.getIsbn())) {//Liburua datu basean ez dago
            Book lib = Sarea.getNireSarea().liburuarenDatuakHasieratu(liburua.getIsbn());
            LiburuDataKud.getInstance().liburuarenDatuSartuDB(lib, liburua.getIsbn());
        }
        labelakEguneratu(LiburuDataKud.getInstance().liburuenDatuenListaLortu(liburua.getIsbn()));
        argitaletxeakEguneratu(LiburuDataKud.getInstance().argitaletxeenZerrenda(liburua.getIsbn()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sarea = Sarea.getNireSarea();
    }
}