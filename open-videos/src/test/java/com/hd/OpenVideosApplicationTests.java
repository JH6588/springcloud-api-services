package com.hd;

import com.hd.repo.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)

@SpringBootTest
public class OpenVideosApplicationTests {
    @Value("${mongodb.database.uri}")
    String pageSize;
    @Test
    public void contextLoads() throws UnsupportedEncodingException {

        System.out.println(pageSize);
    }

}
