package apllication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxViews/urna.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/javafxViews/css/styles.css").toExternalForm());

        stage.setTitle("Simulador de Urna Eletrônica");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
