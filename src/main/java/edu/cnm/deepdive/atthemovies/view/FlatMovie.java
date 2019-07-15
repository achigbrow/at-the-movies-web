package edu.cnm.deepdive.atthemovies.view;

import edu.cnm.deepdive.atthemovies.model.entity.Genre;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

public interface FlatMovie {

  public UUID getId();

  public Date getCreated();

  public Date getUpdated();

  public String getTitle();

  public String getScreenwriter();

  public Genre getGenre();

  public URI getHref();

}
