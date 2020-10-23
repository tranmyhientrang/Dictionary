package Dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class ShowAllController {
    DictionaryManagement DM = new DictionaryManagement();
    private MainController mainController;
    private Stage stage;
    @FXML
    private TextArea textshow;
    @FXML
    private Button close_show;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * hien thi toan bo tu dien.
     * @param event
     * @throws FileNotFoundException
     */
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

    /**
     * Cancel.
     */
    @FXML
    public void close() {
        Stage stage = (Stage) close_show.getScene().getWindow();
        stage.close();
    }

}
