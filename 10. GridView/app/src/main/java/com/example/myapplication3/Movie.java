package com.example.myapplication3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movie {
    private Integer id;
    private String title;
    private Integer pic;
}
