package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

public class setController {
    DictionaryManagement DM = new DictionaryManagement();
    DictionaryCommandline DCL = new DictionaryCommandline();
    private Controller mainController;
    private Stage stage;
    @FXML
    private TextArea set_target;
    @FXML
    private TextArea set_explain;
    @FXML
    private TextArea set_target1;
    @FXML
    private TextArea set_explain1;
    @FXML
    private Button close_set;
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void f_set(ActionEvent event) throws FileNotFoundException {
        DM.insertFromFile();
        Word t1=new Word();
        Word t2=new Word();
        String t= set_target.getText();
        t1.setWord_explain(set_explain.getText());
        t2.setWord_target(set_target1.getText());
        t2.setWord_explain(set_explain1.getText());
        DM.dictionarySet(t1,t2);
        DM.dictionaryExportToFile();
        mainController.getDiction().removeIf(word -> {
            if (word.getWord_target().equals(t)) {
                return true;
            } else {
                return false;
            }
        });
        mainController.getList().getItems().clear();
        mainController.getList().getItems().addAll(mainController.getDiction());
        mainController.getDiction().add(t2);
        mainController.getList().getItems().clear();
        mainController.getList().getItems().addAll(mainController.getDiction());

        if (stage != null) {
            stage.close();
        }
    }






    public void show() {
        if (stage != null) {
            stage.show();
        } else {
            System.out.println("The stage of set controller is null.");
        }
    }
    @FXML
    public void close(){
        Stage stage = (Stage) close_set.getScene().getWindow();
        stage.close();
    }
}
