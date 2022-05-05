package com.project.shortener.urls;

import com.project.shortener.browsers.Browser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Url")
public class Url {

    @Id
    private String shortenedUrl;
    private String longUrl;
    private long clicks;
    private List<Browser> browsers;

    @CreatedDate
    private Instant createdAt;


    public Url(String shortenedUrl, String longUrl) {
        this.shortenedUrl = shortenedUrl;
        this.longUrl = longUrl;
        clicks = 0;
        browsers = new ArrayList<>();
    }
}
