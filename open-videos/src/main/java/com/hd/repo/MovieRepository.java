package com.hd.repo;



import com.hd.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>, MovieRepositoryCustom {

    List<Movie> findMovieByMovieName(String movieName);

    Movie findMovieByDataId(Integer dataId);

    @Query("{ 'source' : ?0 , 'dataId' :{ $gt: ?1 ,$lt:?2} } ")
    List<Movie> findBeetwen(String source, int start, int end);

}
