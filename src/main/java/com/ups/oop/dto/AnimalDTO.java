package com.ups.oop.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class AnimalDTO {
    private String id;
    private double lenght;
    private double height;
    private double weight;
    private String color;
    private String breed;
    private String name;
}