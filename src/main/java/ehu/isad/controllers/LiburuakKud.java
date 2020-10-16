package ehu.isad.controllers;

import ehu.isad.Liburua;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LiburuakKud implements Initializable {

    private Liburua mainApp;

    @FXML
    private Label lbl1;

    @FXML
    private ComboBox<?> comboHautatu;

    @FXML
    private Button buttonIkusi;

    public void setMainApp(Liburua main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent event) {
        mainApp.XehetasunakErakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
