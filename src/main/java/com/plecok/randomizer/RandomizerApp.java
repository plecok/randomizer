package com.plecok.randomizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RandomizerApp extends Application {

    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    private ConfigurableApplicationContext springContext;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(RandomizerApp.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(springContext::getBean);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation(getClass().getResource("../../../ui.fxml"));
        rootNode = fxmlLoader.load();

//        Parent grid = FXMLLoader.load(getClass().getResource("../../../ui.fxml"));

        Scene scene = new Scene(rootNode, 1230, 840);
        primaryStage.setTitle("Prawda co dzisiaj");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
