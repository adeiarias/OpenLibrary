package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburua;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {

    private Liburua mainApp;

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
        mainApp.ComboBoxErakutsi();
    }

    public void labelakEguneratu(Book book){
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}