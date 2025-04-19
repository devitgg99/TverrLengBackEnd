package org.example.tverleng.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.tverleng.base.APIResponse;
import org.example.tverleng.base.BaseController;
import org.example.tverleng.model.entities.Product;
import org.example.tverleng.model.request.ProductRequest;
import org.example.tverleng.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProductController extends BaseController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return response(APIResponse.builder()
                .message("Get all product successfully!!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .payload(products)
                .build());
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        Product products = productService.addProduct(productRequest);
        return response(APIResponse.builder()
                .message("Add product successfully!!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .payload(products)
                .build());
    }



    @GetMapping("categoryId")
    public ResponseEntity<?> getAllProductByCategoryId(@RequestParam("categoryId") Long categoryId) {
        List<Product> products = productService.getAllProductByCategoryId(categoryId);
        return response(APIResponse.builder()
                .message("Get all product successfully!!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .payload(products)
                .build());
    }

}
