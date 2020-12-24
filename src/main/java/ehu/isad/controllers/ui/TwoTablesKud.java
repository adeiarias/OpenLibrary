package ehu.isad.controllers.ui;

import ehu.isad.Model.TwoTables;
import ehu.isad.controllers.db.TwoTablesDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TwoTablesKud {

    @FXML
    private TableView<TwoTables> leftTable;

    @FXML
    private TableColumn<TwoTables, String> firstLeft;

    @FXML
    private TableColumn<TwoTables, String> lastLeft;

    @FXML
    private TableColumn<TwoTables, String> sportLeft;

    @FXML
    private TableColumn<TwoTables, String> yearsLeft;

    @FXML
    private TableColumn<TwoTables, String> vegetarianLeft;

    @FXML
    private TableColumn<TwoTables, String> argazkiLeft;

    @FXML
    private TableView<TwoTables> rightTable;

    @FXML
    private TableColumn<TwoTables, String> firstRight;

    @FXML
    private TableColumn<TwoTables, String> lastRight;

    @FXML
    private TableColumn<TwoTables, String> sportRight;

    @FXML
    private TableColumn<TwoTables, String> yearsRight;

    @FXML
    private TableColumn<TwoTables, String> vegetarianRight;

    @FXML
    private TableColumn<TwoTables, String> argazkiaRight;

    @FXML
    private Button sartu;

    @FXML
    private Button kendu;

    @FXML
    private Button gorde;
    
    private TwoTablesDB twoTablesDB = TwoTablesDB.getInstantzia();

    @FXML
    void OnClick(ActionEvent event) {
        Button btn = (Button) event.getSource();
        if (sartu.equals(btn)){
            klikEzkerraTratatu();
        }else if(kendu.equals(btn)){
            klikEskuinaTratuatu();
        }else{//gorde
            twoTablesDB.deleteFromDatabase();
            ObservableList<TwoTables> list = rightTable.getItems();
            for(int i = 0; i < list.size(); i++){
                twoTablesDB.insertInto(list.get(i));
            }
        }
    }

    private void klikEzkerraTratatu(){
        ObservableList<TwoTables> left = leftTable.getItems();
        ObservableList<TwoTables> right = rightTable.getItems();
        if(leftTable.getSelectionModel().getSelectedItem() != null){
            TwoTables tt = leftTable.getSelectionModel().getSelectedItem();
            left.remove(leftTable.getSelectionModel().getSelectedItem());
            right.add(tt);
        }else{
            if(leftTable.getItems().size() > 0){
                TwoTables tt = leftTable.getItems().get(leftTable.getItems().size()-1);
                left.remove(leftTable.getItems().size()-1);
                right.add(tt);
            }
        }
        leftTable.setItems(left);
        rightTable.setItems(right);
    }

    private void klikEskuinaTratuatu(){
        ObservableList<TwoTables> left = leftTable.getItems();
        ObservableList<TwoTables> right = rightTable.getItems();
        if(rightTable.getSelectionModel().getSelectedItem() != null){
            TwoTables tt = rightTable.getSelectionModel().getSelectedItem();
            right.remove(rightTable.getSelectionModel().getSelectedItem());
            left.add(tt);
        }else{
            if(rightTable.getItems().size() > 0){
                TwoTables tt = rightTable.getItems().get(rightTable.getItems().size()-1);
                right.remove(rightTable.getItems().size()-1);
                left.add(tt);
            }
        }
        leftTable.setItems(left);
        rightTable.setItems(right);
    }

    private void leftItems(){
        firstLeft.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastLeft.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        sportLeft.setCellValueFactory(new PropertyValueFactory<>("sport"));
        yearsLeft.setCellValueFactory(new PropertyValueFactory<>("years"));
        vegetarianLeft.setCellValueFactory(new PropertyValueFactory<>("vegetarian"));
        argazkiLeft.setCellValueFactory(new PropertyValueFactory<>("irudi"));

    }
    
    private void rightItems(){
        firstRight.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastRight.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        sportRight.setCellValueFactory(new PropertyValueFactory<>("sport"));
        yearsRight.setCellValueFactory(new PropertyValueFactory<>("years"));
        vegetarianRight.setCellValueFactory(new PropertyValueFactory<>("vegetarian"));
        argazkiaRight.setCellValueFactory(new PropertyValueFactory<>("irudi"));
    }

    @FXML
    void initialize() {
        leftItems();
        rightItems();
        leftTable.setItems(twoTablesDB.fillFirstTable());
        ObservableList<TwoTables> list = FXCollections.observableArrayList();
        rightTable.setItems(list);
    }
}