package org.example.dto;

public abstract class Item {

    protected String name;

    protected Integer quantity;

    protected String imagePath;

    protected String description;

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Item setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }
}
