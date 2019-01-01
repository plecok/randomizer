package com.plecok.randomizer.model.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by micz on 2019-01-01.
 */
@Data
@Entity
@NoArgsConstructor
public class Movie {
  @Id
  @GeneratedValue
  private long id;
  private String title;
  @ManyToOne
  @JoinColumn(name="director_id")
  private Director director;
  @Enumerated(EnumType.STRING)
  private StorageGroup storageGroup;
  @Temporal(TemporalType.DATE)
  private Date lastSeen;
  private long seenNumber;
}
