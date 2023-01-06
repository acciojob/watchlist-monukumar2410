package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){

         movieService.addMovie(movie);
         return new ResponseEntity<>("success",HttpStatus.CREATED);

    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){

        movieService.addDirector(director);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    } 

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie, @RequestParam String director){

        movieService.addMovieDirectorPair(movie , director);
        return new ResponseEntity<>("success",HttpStatus.CREATED); 
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie ,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){

        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-director-by-movie-name/{name}")
    public ResponseEntity<String> getDirectorByMovieName(@PathVariable String name){

        String director = movieService.getDirectorByMovieName(name);
        return new ResponseEntity<>(director, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){

        List<String> movies = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(movies, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){

        List<String> allMovies = movieService.findAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name){

        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){

        movieService.deleteAllDirectors();
        return new ResponseEntity<>("success",HttpStatus.ACCEPTED);
    }
         
    
}
