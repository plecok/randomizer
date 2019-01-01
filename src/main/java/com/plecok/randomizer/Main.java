package com.plecok.randomizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent grid = FXMLLoader.load(getClass().getResource("../../../ui.fxml"));
        Scene scene = new Scene(grid, 1230, 840);
        primaryStage.setTitle("Prawda co dzisiaj");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
