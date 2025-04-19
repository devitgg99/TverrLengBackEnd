package org.example.tverleng.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long productID;
    private String name;
    private Double price;
    private Long stock;
    private String productImage;
    private Long categoryID;
}
