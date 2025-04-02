package com.example.blog.tutorial.exceptions;

import lombok.Data;

import java.util.UUID;

@Data
public class ResourceNotFoundException extends RuntimeException {
    private String entity;
    private String field;
    private UUID id;
    public ResourceNotFoundException(String entity, String field, UUID id) {
        super(String.format("%s is not found with any %s: %s", entity, field, id));
        this.entity = entity;
        this.field = field;
        this.id = id;
    }
}
