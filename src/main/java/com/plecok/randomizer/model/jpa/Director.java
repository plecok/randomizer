package com.plecok.randomizer.model.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by micz on 2019-01-01.
 */
@Data
@Entity
@NoArgsConstructor
public class Director {
  @Id
  @GeneratedValue
  private long id;
  private String firstName;
  private String lastName;
}
