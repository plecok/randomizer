package com.plecok.randomizer;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by micz on 2018-11-25.
 */
public class Model {

  private final SimpleStringProperty title, director, group, seen;

  public Model(String line) {
    String[] split = line.split(";");
    this.title = new SimpleStringProperty(split[0]);
    this.director = new SimpleStringProperty(split[1]);
    this.group = new SimpleStringProperty(split[2]);
    this.seen = new SimpleStringProperty(split[3]);
  }

  public String getTitle() {
    return title.get();
  }

  public String getDirector() {
    return director.get();
  }

  public String getGroup() {
    return group.get();
  }

  public String getSeen() {
    return seen.get();
  }
}
