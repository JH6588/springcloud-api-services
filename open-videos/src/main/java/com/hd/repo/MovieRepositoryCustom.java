package com.hd.repo;



import com.hd.model.Movie;

import java.util.List;


public interface MovieRepositoryCustom {

    List<Movie> searchMovie(String keyWord, String videoType, String contType, String movieName, String area, String cast, int pageNum, int pageSize);

}
