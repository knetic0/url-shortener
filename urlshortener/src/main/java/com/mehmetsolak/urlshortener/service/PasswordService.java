package com.mehmetsolak.urlshortener.service;

public interface PasswordService {
    String encryptPassword(String password);
    boolean verifyPassword(String password, String encryptedPassword);
}
