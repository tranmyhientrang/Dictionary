package Dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class AddController {
    DictionaryManagement DM = new DictionaryManagement();
    private MainController mainController;
    private Stage stage;

    @FXML
    private TextField add_target;
    @FXML
    private TextField add_explain;
    @FXML
    private Button close_add;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * them tu vao dictionary.
     * @param event
     * @throws FileNotFoundException
     */
    public void f_add(ActionEvent event) throws FileNotFoundException {
        String t1 = add_target.getText();
        String t2 = add_explain.getText();
        DM.insertFromFile();
        DM.dictionaryAdd(t1, t2);
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

    /**
     * hien thi.
     */
    public void show() {
        if (stage != null) {
            stage.show();
        } else {
            System.out.println("The stage of add controller is null.");
        }
    }

    /**
     * Cancel.
     */
    @FXML
    public void close() {
        Stage stage = (Stage) close_add.getScene().getWindow();
        stage.close();
    }
}
