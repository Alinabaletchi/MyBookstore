package org.example.library.serviceInterfaces;

import org.example.library.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    void deleteById(Long id);

    Product updateProduct(Long id, Product product);

    Product updatePartialProduct(Long id, Product product);
}
