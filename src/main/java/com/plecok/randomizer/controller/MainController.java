package com.plecok.randomizer.controller;

import com.plecok.randomizer.model.MovieRow;
import com.plecok.randomizer.service.RandomizeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.nio.file.Files;

import static com.plecok.randomizer.utils.Constants.DB;

@Controller
public class MainController extends AbstractLayoutController{

  private EventHandler<ActionEvent> onStart, onDraw;

  private final RandomizeService randomizeService;

  public MainController(RandomizeService randomizeService) {
    this.randomizeService = randomizeService;
  }

  @FXML
  private void initialize() {
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
    groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
    seenColumn.setCellValueFactory(new PropertyValueFactory<>("seen"));

    populate(load());

    onStart = event -> {
      populate(randomizeService.getRandomElements(tableView.getItems(), onlyNew.isSelected()));
      button.setText("Refresh");
      button.setOnAction(onDraw);
    };
    onDraw = event -> {
      populate(load());
      button.setText("Randomize");
      button.setOnAction(onStart);
    };

    button.setOnAction(onStart);
    searchField.setOnKeyPressed(event -> {
      ObservableList<MovieRow> filtered = FXCollections.observableArrayList();
      String text = searchField.getText().toUpperCase();
      load().stream()
              .filter(model -> "".equals(text)
                      || model.getDirector().toUpperCase().contains(text)
                      || model.getTitle().toUpperCase().contains(text))
              .forEach(filtered::add);
      populate(filtered);
    });
  }

  private void populate(ObservableList<MovieRow> values){
    tableView.setItems(values);
  }

  private ObservableList<MovieRow> load() {
    ObservableList<MovieRow> data = FXCollections.observableArrayList();
    try {
      Files.readAllLines(DB()).stream()
              .map(MovieRow::new)
              .forEach(data::add);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }
}
