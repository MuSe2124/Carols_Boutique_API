/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;

/**
 *
 * @author muaad
 */
public class Product implements Serializable{
    
    private String id;
    private String name;
    private String description;
    private Float price;
	private String size;
	
    public Product(String id, String name, String description, Float price, String size) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
    }
    

    public Product(String name, String description, Float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
     public Product(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public Product(String id) {
        this.id = id;
    }

    
    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + '}';
    }
    
    
}
