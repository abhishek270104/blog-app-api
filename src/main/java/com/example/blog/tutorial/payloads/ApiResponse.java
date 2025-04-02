package com.example.blog.tutorial.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean status;
}
