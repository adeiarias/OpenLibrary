package ehu.isad;

import ehu.isad.ui.LiburuakKud;
import ehu.isad.ui.XehetasunakKud;
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
    private Scene liburuak,xehetasunak;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        pantailakKargatu();
        stage.setTitle("OpenLibrary");
        stage.setScene(liburuak);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderLiburu = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        liburuakUI = (Parent) loaderLiburu.load();
        libKud = loaderLiburu.getController();
        libKud.setMainApp(this);
        liburuak = new Scene(liburuakUI,500,400);

        FXMLLoader loaderXehetasunak = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        xehetasunakUI = (Parent) loaderXehetasunak.load();
        xeheKud = loaderXehetasunak.getController();
        xeheKud.setMainApp(this);
        xehetasunak = new Scene(xehetasunakUI,600,400);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void liburuakErakutsi() {
        stage.setScene(liburuak);
        stage.show();
    }

    public void xehetasunakErakutsi(Book book) throws IOException {
        xeheKud.datuakErakutsi(book);
        stage.setScene(xehetasunak);
        stage.show();
    }
}
