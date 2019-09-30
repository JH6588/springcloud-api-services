package com.hd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hd.common.Result;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

@Controller
public class MovieController {

    @Autowired
    RestTemplate restTemplate;
    @Value("${myvideo.appkey}")
    String appKey;

    @Value("${myvideo.token}")
    String token;

    @GetMapping("/movie")
    public String getMovies(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        String url;
        String appKeyToken = "appkey=" + appKey + "&token=" + token;
        if (StringUtils.isEmpty(request.getQueryString())) {

            url = "http://API-GATEWAY/api/openvideos/movie/search?size=18&" + appKeyToken;
        } else {
            String queryStr = URLDecoder.decode(request.getQueryString(), "UTF-8");
            url = "http://API-GATEWAY/api/openvideos/movie/search?size=18&" + queryStr + "&" + appKeyToken;

        }
        System.out.println(url);
        Result result = restTemplate.getForObject(url, Result.class);

        if (result == null) {
            return "error";
        }
        if (result.getCode() != 200 || result.getObject() == null) {
            result.setObject(new ArrayList<>());
        }
        model.addAttribute("movieList", result.getObject());
        return "index";

    }


    @GetMapping("/col/{dataId}")
    public String getMovieInfo(@PathVariable("dataId") String dataId, Model model) {
        String appKeyToken = "appkey=" + appKey + "&token=" + token;
        Result result = restTemplate.getForObject("http://API-GATEWAY/api/openvideos/movie/" + dataId + "?" + appKeyToken, Result.class);
        System.out.println(result);
        if (result != null && result.getObject() != null) {
            ObjectMapper mapper = new ObjectMapper();
//            Movie movie = mapper.convertValue( result.getObj() ,Movie.class);
//            System.out.println("MOVIE  " +movie);
            model.addAttribute("movie", result.getObject());
            return "col";
        }
        return "error";
    }

}


