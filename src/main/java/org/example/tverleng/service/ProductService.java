package org.example.tverleng.service;

import org.example.tverleng.model.entities.Product;
import org.example.tverleng.model.request.ProductRequest;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product addProduct(ProductRequest productRequest);

    List<Product> getAllProductByCategoryId(Long categoryId);
}
