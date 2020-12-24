package ehu.isad;

import ehu.isad.controllers.ui.MessageKud;
import ehu.isad.controllers.ui.TwoTablesKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Parent TwoTablesUI;
    private Stage stage;
    private TwoTablesKud two;
    private Scene tables;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailaHasieratu();
        stage.setTitle("MESSAGE ARIKETA");
        stage.setScene(tables);
        stage.show();
    }

    public void pantailaHasieratu() throws IOException {
        FXMLLoader loaderMezuak = new FXMLLoader(getClass().getResource("/TwoTales.fxml"));
        TwoTablesUI = (Parent)loaderMezuak.load();
        two = loaderMezuak.getController();
        tables = new Scene(TwoTablesUI);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
