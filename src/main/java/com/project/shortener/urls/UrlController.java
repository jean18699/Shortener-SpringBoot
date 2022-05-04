package com.project.shortener.urls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.net.URI;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UrlController {

    @Autowired
    UrlServiceImpl urlService;

    @Autowired
    UrlDao urlDao;

    @PostMapping(value = "/sh", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void shorten(@RequestBody Url url){
        urlService.shortenUrl(url.getLongUrl());
    }

    //Go to the url of the shortened link
    @GetMapping("{shortenedUrl}")
    ResponseEntity<Void> redirect(@PathVariable String shortenedUrl) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.retrieveUrl(shortenedUrl).getLongUrl()))
                .build();
    }
}
