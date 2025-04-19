package org.example.tverleng.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.tverleng.model.entities.Product;
import org.example.tverleng.model.request.ProductRequest;
import org.example.tverleng.repository.ProductRepository;
import org.example.tverleng.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public Product addProduct(ProductRequest productRequest) {
        Product product = productRepository.addProduct(productRequest);
        productRepository.insertIntoProductCategory(product.getProductID(), product.getCategoryID());
        return product;
    }

    @Override
    public List<Product> getAllProductByCategoryId(Long categoryId) {
        return productRepository.getAllProductByCategoryId(categoryId);
    }
}
