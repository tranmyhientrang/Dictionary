package Dictionary;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private TextField target;
    @FXML
    private Button button;
    @FXML
    private ListView<Word> list;
    @FXML
    private TextArea textArea;
    @FXML
    private Button add;
    @FXML
    private Button sound;
    @FXML
    private List<Word> diction = new ArrayList<>();

    @FXML
    DictionaryCommandline DCL = new DictionaryCommandline();
    DictionaryManagement DM = new DictionaryManagement();

    /**
     * cua so add.
     * @param event
     * @throws IOException
     */
    @FXML
    void wordAdd(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/add.fxml"));
        Parent root = fxmlLoader.load();
        AddController addController = fxmlLoader.getController();
        addController.setMainController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        addController.setStage(stage);
        addController.show();
    }

    /**
     * cua so del.
     * @param event
     * @throws IOException
     */
    @FXML
    void wordDel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/delete.fxml"));
        Parent root = fxmlLoader.load();
        DelController d = fxmlLoader.getController();
        d.setMainController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        d.setStage(stage);
        d.show();
    }

    /**
     * cua so set.
     * @param event
     * @throws IOException
     */
    @FXML
    void wordSet(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/set.fxml"));
        Parent root = fxmlLoader.load();
        SetController s = fxmlLoader.getController();
        s.setMainController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        s.setStage(stage);
        s.show();
    }

    /**
     * cua so show.
     * @param event
     * @throws IOException
     */
    @FXML
    void wordShow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/showall.fxml"));
        Parent root = fxmlLoader.load();
        ShowAllController show = new ShowAllController();
        show.setMainController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        show.setStage(stage);
        show.show();
    }

    public List<Word> getDiction() {
        return diction;
    }

    public ListView<Word> getList() {
        return list;
    }

    /**
     * hien thi ListView va thao tac tren ListView.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            DM.insertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < DM.d.getN(); i++) {
            diction.add(DM.d.words[i]);
        }
        list.getItems().addAll(diction);

        list.setCellFactory(new Callback<ListView<Word>, ListCell<Word>>() {
            @Override
            public ListCell<Word> call(ListView<Word> param) {
                return new Mycell();
            }
        });

        target.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String t = target.getText();
                List<Word> matchedWords = new ArrayList<>(diction);
                matchedWords.removeIf(word -> {
                    if (word.getWord_target().startsWith(t)) {
                        return false;
                    } else {
                        return true;
                    }
                });
                list.getItems().clear();
                list.getItems().addAll(matchedWords);

                list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>() {

                    @Override
                    public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue) {
                        if (newValue != null) {
                            target.setText(newValue.getWord_target());
                            textArea.setText(newValue.getWord_target() + "\n" + newValue.getWord_explain());
                        }
                    }

                });

            }
        });

    }

    /**
     * tim kiem tu.
     * @param event
     * @throws FileNotFoundException
     */
    public void submit(ActionEvent event) throws FileNotFoundException {
        DM.insertFromFile();
        String t = target.getText();
        textArea.setText(DM.dictionaryLookup(t));

    }

    public static class Mycell extends ListCell<Word> {
        @Override
        protected void updateItem(Word item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getWord_target());
            } else {
                setText(null);
            }
        }
    }

    /**
     * Audio
     * @param event
     */
    public void action(ActionEvent event) {

        Audio audio = Audio.getInstance();
        InputStream sound = null;
        if (target.getText().trim().isEmpty()) {
            try {
                sound = audio.getAudio("Enter English words in the search box", "en");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        } else {
            try {
                sound = audio.getAudio(target.getText(), "en");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                audio.play(sound);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }
}