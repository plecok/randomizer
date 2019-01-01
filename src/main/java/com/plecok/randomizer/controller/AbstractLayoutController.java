package com.plecok.randomizer.controller;

import com.plecok.randomizer.model.MovieRow;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by micz on 2019-01-01.
 */
public abstract class AbstractLayoutController {

  @FXML
  protected TableView<MovieRow> tableView;

  @FXML
  protected TableColumn<MovieRow, String> titleColumn;

  @FXML
  protected TableColumn<MovieRow, String> directorColumn;

  @FXML
  protected TableColumn<MovieRow, String> groupColumn;

  @FXML
  protected TableColumn<MovieRow, String> seenColumn;

  @FXML
  protected Button button;

  @FXML
  protected CheckBox onlyNew;

  @FXML
  protected TextField searchField;
}
