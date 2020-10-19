package ehu.isad.ui;

import ehu.isad.Book;
import ehu.isad.Liburua;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuakKud implements Initializable {

    private Liburua mainApp;

    @FXML
    private Label lbl1;

    @FXML
    private ComboBox<Book> comboHautatu;

    @FXML
    private Button buttonIkusi;

    public void setMainApp(Liburua main) {
        this.mainApp = main;
    }

    @FXML
    public void onClick(ActionEvent event) throws IOException {
        Book book = (Book)comboHautatu.getValue();
        mainApp.xehetasunakErakutsi(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> liburuak = FXCollections.observableArrayList();
        liburuak.addAll(
                new Book("Blockchain: Blueprint for a New Economy","9781491920497"),
                new Book("R for Data Science","1491910399"),
                new Book("Fluent Python","1491946008"),
                new Book("Natural Language Processing with PyTorch","1491978236"),
                new Book("Data Algorithms","9781491906187")
        );
        comboHautatu.setItems(liburuak);
        comboHautatu.setEditable(false);
    }
}
