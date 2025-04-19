package org.example.tverleng.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.tverleng.model.entities.Product;
import org.example.tverleng.model.request.ProductRequest;

import java.util.List;

@Mapper
public interface ProductRepository {

    @Select("""
        SELECT * from product;
    """)
    List<Product> getAllProduct();


    @Select("""
    INSERT INTO product (productid,name, price, stock, productimage, categoryid)
    VALUES (default,#{req.name}, #{req.price}, #{req.stock}, #{req.productImage}, #{req.categoryID})
    RETURNING *;
""")
    Product addProduct(@Param("req") ProductRequest productRequest);


    @Select("""
        SELECT p.*
        FROM Product p
        JOIN ProductCategory pc ON p.productID = pc.productID
        WHERE pc.categoryID = #{categoryId};
    """)
    List<Product> getAllProductByCategoryId(Long categoryId);


    @Select("""
        INSERT INTO productcategory(productid, categoryid)
        VALUES (#{productID},#{CategoryID});
    """)
    void insertIntoProductCategory(Long productID, Long CategoryID);
}
