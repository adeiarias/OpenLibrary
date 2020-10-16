package ehu.isad;

import ehu.isad.controllers.LiburuakKud;
import ehu.isad.controllers.XehetasunakKud;
import ehu.isad.utils.Sarea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Liburua extends Application {

    private Parent liburuakUI;
    private Parent xehetasunakUI;

    private Stage stage;
    private LiburuakKud libKud;
    private XehetasunakKud xeheKud;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailakKargatu();
        stage.setTitle("OpenLibrary");
        stage.setScene(new Scene(xehetasunakUI, 500, 400));
        stage.show();
    }

    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderLiburu = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        liburuakUI = (Parent) loaderLiburu.load();
        libKud = loaderLiburu.getController();
        libKud.setMainApp(this);

        FXMLLoader loaderXehetasunak = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        xehetasunakUI = (Parent) loaderXehetasunak.load();
        xeheKud = loaderXehetasunak.getController();
        xeheKud.setMainApp(this);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void ComboBoxErakutsi() {
        stage.setScene(new Scene(liburuakUI,500,400));
        stage.show();
    }

    public void XehetasunakErakutsi() {
        stage.setScene(new Scene(xehetasunakUI,500,400));
        stage.show();
    }
}
