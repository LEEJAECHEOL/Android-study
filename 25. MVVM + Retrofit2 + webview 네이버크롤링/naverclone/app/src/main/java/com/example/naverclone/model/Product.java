package com.example.naverclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String blogUrl;
    private String title;
    private String thumnail;
    private String day;
}
