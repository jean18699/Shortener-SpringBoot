package com.project.shortener.urls;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UrlService {


    public String formatURL(String url);
    public String generateKey();
    public Url shortenUrl(String longUrl);
    public Url retrieveUrl(String shortenedUrl);
    public Url deleteUrl(String shortenedUrl);
    public List<Url> getAllUrls();

}
