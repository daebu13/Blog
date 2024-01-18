package com.post.blog.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogDto {

    private long id;

    private String title;

    private String description;

    private String content;
}
