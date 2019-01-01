package com.plecok.randomizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.nio.file.Files;
import java.util.Collections;

import static com.plecok.randomizer.Constants.DB;

public class Controller {

  @FXML
  private TableView<Model> tableView;

  @FXML
  private TableColumn<Model, String> titleColumn;

  @FXML
  private TableColumn<Model, String> directorColumn;

  @FXML
  private TableColumn<Model, String> groupColumn;

  @FXML
  private TableColumn<Model, String> seenColumn;

  @FXML
  private Button button;

  @FXML
  private CheckBox onlyNew;

  @FXML
  private TextField searchField;

  private EventHandler<ActionEvent> onStart, onDraw;

  @FXML
  private void initialize() {
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
    groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
    seenColumn.setCellValueFactory(new PropertyValueFactory<>("seen"));

    populate(load());

    onStart = event -> {
      populate(getRandomElements(tableView.getItems()));
      button.setText("Refresh");
      button.setOnAction(onDraw);
    };
    onDraw = event -> {
      populate(load());
      button.setText("Start");
      button.setOnAction(onStart);
    };

    button.setOnAction(onStart);
    searchField.setOnKeyPressed(event -> {
      ObservableList<Model> filtered = FXCollections.observableArrayList();
      String text = searchField.getText().toUpperCase();
      load().stream()
              .filter(model -> "".equals(text)
                      || model.getDirector().toUpperCase().contains(text)
                      || model.getTitle().toUpperCase().contains(text))
              .forEach(filtered::add);
      populate(filtered);
    });
  }

  private void populate(ObservableList<Model> values){
    tableView.setItems(values);
  }

  private ObservableList<Model> load() {
    ObservableList<Model> data = FXCollections.observableArrayList();
    try {
      Files.readAllLines(DB()).stream()
              .map(Model::new)
              .forEach(data::add);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }

  private ObservableList<Model> getRandomElements(ObservableList<Model> elements) {
    ObservableList<Model> models = FXCollections.observableArrayList();
    elements.stream()
            .filter(model -> "NO".equals(model.getSeen()) || !onlyNew.isSelected())
            .forEach(models::add);
    Collections.shuffle(models);
    return FXCollections.observableArrayList(models.subList(0, 3));
  }

}
