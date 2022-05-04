package com.project.shortener;

import com.project.shortener.urls.UrlService;
import com.project.shortener.urls.UrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ShortenerApplication {

    @Autowired
    UrlServiceImpl urlService;


    public static void main(String[] args) {

        SpringApplication.run(ShortenerApplication.class, args);


    }


}
