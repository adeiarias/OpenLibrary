package ehu.isad.controllers.ui;

import ehu.isad.Model.Message;
import ehu.isad.controllers.db.MessageDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MessageKud {

    @FXML
    private TableView<Message> tableview;

    @FXML
    private TableColumn<Message, String> fromColumn;

    @FXML
    private TableColumn<Message, String> toColumn;

    @FXML
    private TableColumn<Message, String> messageColumn;

    @FXML
    private Button button;

    private MessageDB messageDB =MessageDB.getInstantzia();

    @FXML
    void Onclick(ActionEvent event) {
        messageDB.deleteDatabase();
        ObservableList<Message> lista = tableview.getItems();
        for(int i = 0; i < lista.size(); i++){
            messageDB.insertInto(lista.get(i));
        }
    }

    void columns(){
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
    }

    void editColumns(){
        fromColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fromColumn.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFrom(e.getNewValue());
        });
        toColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        toColumn.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTo(e.getNewValue());
        });
        messageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        messageColumn.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMessage(e.getNewValue());
        });
    }

    @FXML
    void initialize() {
        columns();
        editColumns();
        tableview.setEditable(true);
        tableview.setItems(messageDB.hasieratuPanela());
    }

}
