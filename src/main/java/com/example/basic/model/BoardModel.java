package com.example.basic.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BoardModel {
    @Id
    Integer id;

    String title;
}
