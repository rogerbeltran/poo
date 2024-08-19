package com.ups.oop.dto;

public class Animal {
    private String id;
    private double lenght;
    private double height;
    private double weight;
    private String color;
    private String breed;
    private String name;

    public Animal() {
    }

    public Animal(String id, double lenght, double height, double weight, String color, String breed, String name) {
        this.id = id;
        this.lenght = lenght;
        this.height = height;
        this.weight = weight;
        this.color = color;
        this.breed = breed;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}