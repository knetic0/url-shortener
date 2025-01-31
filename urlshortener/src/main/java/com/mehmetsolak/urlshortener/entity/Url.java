package com.mehmetsolak.urlshortener.entity;

import com.mehmetsolak.urlshortener.dto.response.UrlDtoResponse;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public final class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String shortUrl;

    private String originalUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Url() { }

    public Url(String shortUrl, String originalUrl, User user) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
        this.user = user;
    }

    public UrlDtoResponse toDto() {
        return new UrlDtoResponse(shortUrl, originalUrl);
    }
}
