package com.plecok.randomizer.service;

import com.plecok.randomizer.model.MovieRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by micz on 2019-01-01.
 */
@Service
public class RandomizeService {

  public ObservableList<MovieRow> getRandomElements(ObservableList<MovieRow> elements, boolean onlyNew) {
    ObservableList<MovieRow> movieRows = FXCollections.observableArrayList();
    elements.stream()
            .filter(model -> "NO".equals(model.getSeen()) || !onlyNew)
            .forEach(movieRows::add);
    Collections.shuffle(movieRows);
    return FXCollections.observableArrayList(movieRows.subList(0, 3));
  }
}
