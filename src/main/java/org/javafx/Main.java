package org.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/org.javafx/urna.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/org.javafx/styles.css").toExternalForm());

        stage.setTitle("Simulador de Urna Eletrônica");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
