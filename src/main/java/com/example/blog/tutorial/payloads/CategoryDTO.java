package com.example.blog.tutorial.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private UUID categoryId;
    private String categoryTitle;
}
