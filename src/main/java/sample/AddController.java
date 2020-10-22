package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddController {
    DictionaryManagement DM = new DictionaryManagement();
    private Controller mainController;
    private Stage stage;

    @FXML
    private TextField add_target;
    @FXML
    private TextField add_explain;
    @FXML
    private Button close_add;

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void f_add (ActionEvent event) throws FileNotFoundException {
        String t1 = add_target.getText();
        String t2 = add_explain.getText();
        DM.insertFromFile();
        DM.dictionaryAdd(t1,t2);
        DM.dictionaryExportToFile();
        Word word = new Word();
        word.setWord_target(t1);
        word.setWord_explain(t2);
        mainController.getDiction().add(word);
        mainController.getList().getItems().clear();
        mainController.getList().getItems().addAll(mainController.getDiction());

        // close stage
        if (stage != null) {
            stage.close();
        }
    }


    public void show() {
        if (stage != null) {
            stage.show();
        } else {
            System.out.println("The stage of add controller is null.");
        }
    }
    @FXML
    public void close(){
        Stage stage = (Stage) close_add.getScene().getWindow();
        stage.close();
    }
}
