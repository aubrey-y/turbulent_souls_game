package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Weapon.class, name = "WEAPON"),
        @JsonSubTypes.Type(value = Potion.class, name = "POTION")
})
public abstract class Item {

    protected String name;

    protected Integer quantity;

    protected String imagePath;

    protected String description;

    protected int price;

    protected int listingPriority;

    public Item() {

    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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

    public int getListingPriority() {
        return listingPriority;
    }

    public Item setListingPriority(int listingPriority) {
        this.listingPriority = listingPriority;
        return this;
    }
}
