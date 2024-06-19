package com.tutorial.bootwebapp.post;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}