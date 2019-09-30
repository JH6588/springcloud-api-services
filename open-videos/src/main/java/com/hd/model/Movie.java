package com.hd.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "ori")
@ToString
public class Movie {
    @Id
    private String movieId;
    private String movieName;
    private String pic;
    private String director;
    private String cast;
    private String area;
    private Integer year;
    private String doubanLink;
    private String info;
    private Integer dataId;
    private String videoType;
    private String contType;
    private List<Provider> providers;

}
