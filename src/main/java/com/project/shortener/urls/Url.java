package com.project.shortener.urls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("Url")
public class Url {

    @Id
    private String shortenUrl;
    private String longUrl;



}
