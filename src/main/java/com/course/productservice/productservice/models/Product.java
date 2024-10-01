package com.course.productservice.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String titile;
    private String description;
    private double price;
    private String image;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn
    private Category category;

    /*
    product => category
    m   => 1
   1    => 1
   cardinality => M:1

    */

}
