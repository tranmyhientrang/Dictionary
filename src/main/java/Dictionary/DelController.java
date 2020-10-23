package Dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class DelController {
    DictionaryManagement DM = new DictionaryManagement();
    private MainController mainController;
    private Stage stage;
    @FXML
    private TextField del_word;
    @FXML
    private Button close_del;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * xoa tu khoi dictionary.
     * @param event
     * @throws FileNotFoundException
     */
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

    /**
     * hien thi.
     */
    public void show() {
        if (stage != null) {
            stage.show();
        } else {
            System.out.println("The stage of delete controller is null.");
        }
    }

    /**
     * Cancel.
     */
    @FXML
    public void close() {
        Stage stage = (Stage) close_del.getScene().getWindow();
        stage.close();
    }
}
