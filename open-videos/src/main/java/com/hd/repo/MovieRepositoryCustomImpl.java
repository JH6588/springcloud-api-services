package com.hd.repo;

import com.hd.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.repository.Query;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {
    @Value("${mongo.max.pagesize}")
    Integer maxPageSize;

    @Resource
    MongoOperations mongoOperations;

    @Override
    public List<Movie> searchMovie(String keyWord, String videoType, String contType, String movieName, String area, String cast, int pageNum, int pageSize) {
        Query query = new Query();
        if(pageSize> maxPageSize){
            pageSize = maxPageSize;
        }


        query.skip(pageNum * pageSize);
        query.limit(pageSize);

        Criteria criteria = new Criteria();


        if (keyWord != null && keyWord.length() != 0) {
            criteria.orOperator(Criteria.where("movieName").regex(".*" + keyWord + ".*"),
                    Criteria.where("cast").regex(".*" + keyWord + ".*")
            );


        }
        List<Criteria> criteriaList = new ArrayList<>();
        if (videoType != null) {
            criteriaList.add(Criteria.where("videoType").is(videoType));
        }
        if (contType != null) {

            criteriaList.add(Criteria.where("contType").regex(".*" +contType +".*"));
        }
        if (area != null) {

            criteriaList.add(Criteria.where("area").is(area));
        }

        if (movieName != null) {

            criteriaList.add(Criteria.where("movieName").regex(".*" + movieName + ".*"));
        }
        if (cast != null) {

            criteriaList.add(Criteria.where("cast").regex(".*" + cast + ".*"));
        }
        if (!criteriaList.isEmpty()) {
            criteria.andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
        }

        query.addCriteria(criteria);


        return mongoOperations.find(query, Movie.class);
//        return mongoTemplate.find(query,Movie.class);
    }


}