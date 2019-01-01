package com.plecok.randomizer.utils;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by micz on 2018-11-25.
 */
public class Constants {

  public static Path DB() {
    try {
      return Paths.get(Constants.class.getResource("../../../../db.txt").toURI());

    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}

