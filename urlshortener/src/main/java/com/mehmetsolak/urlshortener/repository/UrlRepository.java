package com.mehmetsolak.urlshortener.repository;

import com.mehmetsolak.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer>{
    Optional<Url> findByShortUrl(String shortUrl);
}
