package com.project.shortener.urls;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("shortener")
public interface UrlDao extends MongoRepository<Url, String> {

    @Query("{shortenedUrl: {$eq: ?0}}")
    Optional<Url> findByShortenUrl(String shortenUrl);

    List<Url> findAll();




    long count();

}
