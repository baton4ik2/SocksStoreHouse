package com.project.Socks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue
    private Long id;  //id

    private String color; //Цвет носков
    private int cottonPart; //процентное содержание хлопка в составе носков, целое число от 0 до 100
    private int quantity; //количество пар носков, целое число больше 0

    public Socks() {

    }

    public Socks(Long id, String color, int cottonPart, int quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Socks socks)) return false;
        return Objects.equals(id, socks.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Socks: " + "id=" + id +
                ", color=" + color +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity;
    }
}
