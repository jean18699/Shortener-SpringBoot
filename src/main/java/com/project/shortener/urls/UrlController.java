package com.project.shortener.urls;

import com.project.shortener.browsers.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
        urlService.shortenUrl(url);
    }

    //Go to the url of the shortened link
    @GetMapping("{shortenedUrl}")
    ResponseEntity<Void> redirect(@PathVariable String shortenedUrl, HttpServletRequest httpReq, Browser browser) {

        System.out.println("mira esto: " + shortenedUrl);
        Url url = urlService.retrieveUrl(shortenedUrl);

        String userAgent = httpReq.getHeader("User-Agent");
        String os;
        String name = "";


        System.out.println(userAgent);

        //Retrieving the Os of the client
        if (userAgent.toLowerCase().contains("windows"))
        {
            os = "Windows";
        } else if(userAgent.toLowerCase().contains("mac"))
        {
            os = "Mac";
        } else if(userAgent.toLowerCase().contains("x11"))
        {
            os = "Unix";
        } else if(userAgent.toLowerCase().contains("android"))
        {
            os = "Android";
        } else if(userAgent.toLowerCase().contains("iphone"))
        {
            os = "IPhone";
        }else{
            os = "UnKnown, More-Info: "+userAgent;
        }

        browser.setIp(httpReq.getRemoteAddr());
        browser.setOs(os);
        url.getBrowsers().add(browser);
        url.setClicks(url.getClicks() + 1);

        urlDao.save(url);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.getLongUrl()))
                .build();
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon(){

    }

}
