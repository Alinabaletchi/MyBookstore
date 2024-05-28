package org.example.library.service;


import org.example.library.entity.Product;
import org.example.library.repository.ProductRepository;
import org.example.library.serviceInterfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            return null;
        }
    }


    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()) {
            Product updateProduct = foundProduct.get();
            updateProduct.setName(product.getName());
            updateProduct.setDescription(product.getDescription());
            updateProduct.setPrice(product.getPrice());
            updateProduct.setQuantity(product.getQuantity());
            updateProduct.setImage(product.getImage());
            return productRepository.save(updateProduct);
        } else {

            return null;
        }
    }

    @Override
    public Product updatePartialProduct(Long id, Product product) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()) {
            Product updateProduct = foundProduct.get();
            if (null != product.getName()) {
                updateProduct.setName(product.getName());
            }
            if (null != product.getDescription()) {
                updateProduct.setDescription(product.getDescription());
            }
            if (0 != product.getPrice()) {
                updateProduct.setPrice(product.getPrice());
            }
            if (0 != product.getQuantity()) {
                updateProduct.setQuantity(product.getQuantity());
            }
            if (null != product.getImage()) {
                updateProduct.setImage(product.getImage());
            }
            return productRepository.save(updateProduct);
        } else {
            return null;
        }
    }

}
