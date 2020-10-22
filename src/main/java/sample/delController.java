package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class delController {
    DictionaryManagement DM = new DictionaryManagement();
    private Controller mainController;
    private Stage stage;
    @FXML
    private TextField del_word;
    @FXML
    private Button close_del;
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

   public void f_del(ActionEvent event) throws FileNotFoundException {
        String t1 = del_word.getText();
        DM.insertFromFile();
        DM.dictionaryDelete(t1);
        DM.dictionaryExportToFile();
       mainController.getDiction().removeIf(word -> {
           if (word.getWord_target().equals(t1)) {
               return true;
           } else {
               return false;
           }
       });
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
            System.out.println("The stage of delete controller is null.");
        }
    }
    @FXML
    public void close(){
        Stage stage = (Stage) close_del.getScene().getWindow();
        stage.close();
    }
}
