package com.example.myapplication10;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Talk {
    private Integer id;
    private String username;
    private String message;
    private String time;
}
