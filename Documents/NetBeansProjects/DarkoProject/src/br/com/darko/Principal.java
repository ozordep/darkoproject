package br.com.darko;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {

    private static Stage stage;
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent painel = FXMLLoader.load(getClass().getResource("/br/com/view/Principal.fxml"));
        scene = new Scene(painel);
        stage.setScene(scene);
        stage.setTitle("Darko Library System - Tela principal");
        scene.getStylesheets().add("/br/com/styles/style.css");
        stage.show();
        stage.requestFocus();
        setStage(stage);
        setScene(scene);

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Principal.stage = stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        Principal.scene = scene;
    }

}
