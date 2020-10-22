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
public class ShowAllController {
    DictionaryManagement DM = new DictionaryManagement();
    private Controller mainController;
    private Stage stage;
    @FXML
    private TextArea textshow;
    @FXML
    private Button close_show;
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void f_show(ActionEvent event) throws FileNotFoundException {
        DM.insertFromFile();
        textshow.setText(DM.showAllWords());
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
        Stage stage = (Stage) close_show.getScene().getWindow();
        stage.close();
    }

}
