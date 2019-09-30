package com.hd.controller;

import com.hd.common.Result;
import com.hd.model.Movie;
import com.hd.model.Provider;
import com.hd.repo.MovieRepository;
import com.hd.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    @Resource
    private MovieRepository movieRepository;


    @GetMapping("/movie/{dataId}")
    @ResponseBody
    public Result findMovieByDataId(@PathVariable("dataId") int dataId) {

        return new Result(200, "success", movieRepository.findMovieByDataId(dataId));
    }


    @GetMapping("/movie/between")
    @ResponseBody
    public Result findMovieBetween(String source, int start, int end) {
        if (end - start > 10) {
            end = start + 10;
        }
        return new Result(200, "success", movieRepository.findBeetwen(source, start, end));

    }

    @GetMapping("/movie/search")
    @ResponseBody
    public Result searchMovies(@RequestParam(name = "key", required = false) String keyWord,
                               @RequestParam(required = false, name = "area") String area,
                               @RequestParam(required = false, name = "videotype") String videoType,
                               @RequestParam(required = false, name = "cast") String cast,
                               @RequestParam(required = false, name = "conttype") String contType,
                               @RequestParam(required = false, name = "moviename") String movieName,
                               @RequestParam(name = "pn", defaultValue = "1") int pageNum,
                               @RequestParam(name = "size", defaultValue = "10") int pageSize) {

        return new Result(200, "success", movieRepository.searchMovie(keyWord, videoType, contType, movieName, area, cast, pageNum, pageSize));

    }

    @GetMapping("/mm")
    @ResponseBody
    public Result getMovieM(@ModelAttribute Movie movie) {

        System.out.println(movie);
        return new Result(200, "success", movie);
    }

    @GetMapping("/nn")
    @ResponseBody
    public Movie getMovien(Movie movie) {

        System.out.println(movie);
        return movie;
    }


}
