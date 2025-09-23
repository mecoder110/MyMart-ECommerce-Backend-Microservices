package com.murtaza.mymart.product.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;
    private String description;
    private String title;
    private String imgUrl;
    private double unitPrice;
    private boolean active;
    private Long unitStock;

    @CreationTimestamp
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp updatedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
