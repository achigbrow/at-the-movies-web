package edu.cnm.deepdive.atthemovies.controller;

import edu.cnm.deepdive.atthemovies.model.dao.GenreRepository;
import edu.cnm.deepdive.atthemovies.model.dao.MovieRepository;
import edu.cnm.deepdive.atthemovies.model.entity.Genre;
import edu.cnm.deepdive.atthemovies.model.entity.Movie;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Genre.class)
@RequestMapping("genres")
public class GenreController {

  private final GenreRepository repository;
  private final MovieRepository movieRepository;

  @Autowired
  public GenreController(GenreRepository repository,
      MovieRepository movieRepository) {
    this.repository = repository;
    this.movieRepository = movieRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Genre> get() {
    return repository.getAllByOrderByNameAsc();
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Genre get(@PathVariable("id") UUID id) {
    return repository.findById(id).get();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Genre> post(@RequestBody Genre genre) {
    repository.save(genre);
    return ResponseEntity.created(genre.getHref()).body(genre);
  }

  @DeleteMapping(value = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Transactional
  public void delete(@PathVariable("id") UUID genreId) {
    Genre genre = get(genreId);
    List<Movie> movies = genre.getMovies();
    movies.forEach((movie) -> movie.setGenre(null) );
//    for (Movie movie : movies) {
//      movie.setGenre(null);
//    }
    movieRepository.saveAll(movies);
    repository.delete(genre);
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {}

}
