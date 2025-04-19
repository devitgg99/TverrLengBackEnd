package org.example.tverleng.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private Double price;
    private Long stock;
    private String productImage;
    private Long categoryID;
}
