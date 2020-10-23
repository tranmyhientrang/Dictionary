package Dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * chay chuong trinh.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dictionary.fxml"));
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
