package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class MovieRepository {
    
    HashMap<String , Movie> movieMap = new HashMap<>();
    HashMap<String , Director> directorMap = new HashMap<>();
    HashMap<String , List<String>> directorMovieMap = new HashMap<>();

    public void addMovie(Movie movie){

        movieMap.put(movie.getName() , movie);
    }

    public void addDirector(Director director){

        directorMap.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director){
        
        List<String> pairList = new ArrayList<>();
        
        if(directorMovieMap.containsKey(director))
        pairList = directorMovieMap.get(director);

        if(!pairList.contains(movie))
        pairList.add(movie);

        directorMovieMap.put(director , pairList);
    }

    public Movie getMovieByName(String name){

        return movieMap.get(name);
    }

    public Director getDirectorByName(String name){

        return directorMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String name){

        return directorMovieMap.get(name);
    }

    public List<String> findAllMovies(){
        
        List<String> movies = new ArrayList<>();
        for(String movieName : movieMap.keySet()){
            movies.add(movieName);
        }

        return movies;
    }

    public void deleteDirectorByName(String name){
        
        if(directorMap.containsKey(name))
        directorMap.remove(name);
        List<String> movies = new ArrayList<>();

        if(directorMovieMap.containsKey(name))
        movies = directorMovieMap.get(name);

        for(String m : movies){
            if(movieMap.containsKey(m))
            movieMap.remove(m);
        }
        if(directorMovieMap.containsKey(name));
        directorMovieMap.remove(name);
    }

    public void deleteAllDirectors(){

        for(String name : movieMap.keySet()){
            
            for(List<String> movies : directorMovieMap.values()){

                if(movies.contains(name))
                movieMap.remove(name);
            }
        }
        directorMap.clear();
        directorMovieMap.clear();
    }



}
