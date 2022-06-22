/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author muaad
 */
public class Review implements Serializable{
    
    private String id;
    private String comment;
    private Integer rating;
    private Date date;

    public Review(String id, String comment, Integer rating, Date date) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }

    public Review(String comment, Integer rating) {
        this.comment = comment;
        this.rating = rating;
    }
    
    

    public Review() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", comment=" + comment + ", rating=" + rating + ", date=" + date + '}';
    }
    
    
}
