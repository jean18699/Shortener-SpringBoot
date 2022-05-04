package com.project.shortener.urls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class UrlServiceImpl implements UrlService {


    @Autowired
    private UrlDao urlDao;

    private String ALFABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int shortenUrlLenght = 6;


    @Override
    public Url shortenUrl(String longUrl) {
        String shortenedUrl = generateKey();
        //String formatedUrl = formatURL(longUrl);
        Url url = new Url(shortenedUrl, longUrl);
        urlDao.save(url);
        return url;
    }

    @Override
    public Url retrieveUrl(String shortenedUrl) {
        Url url = urlDao.findByShortenUrl(shortenedUrl).orElseThrow(()-> new UrlException("This Url does not exists"));
        return url;
    }

    @Override
    public Url deleteUrl(String shortenedUrl) {
        Url url = urlDao.findByShortenUrl(shortenedUrl).orElseThrow(()-> new UrlException("This Url does not exists"));
        urlDao.delete(url);
        return url;
    }

    @Override
    public List<Url> getAllUrls() {
        return urlDao.findAll();
    }

    @Override
    public String formatURL(String url) {
        if (url.startsWith("http://"))
            url = url.substring(7);

        if (url.startsWith("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

    @Override
    public String generateKey() {

        Random rand = new Random();
        String key = "";


        while (true) {
            key = "";

            //generating key
            for (int i = 1; i <= shortenUrlLenght; i++) {
                key += ALFABET.charAt(rand.nextInt(62));
            }

            //Si la clave no se encuentra en la base de datos significa que no existe y que se puede usar, terminando asi la generacion

            if(!urlDao.findByShortenUrl(key).isPresent()){
                break;
            }


        }
        System.out.println(key);
        return key;
    }




}
