package org.example.library.controller;

import org.example.library.entity.Book;
import org.example.library.entity.Product;
import org.example.library.enumes.BookType;
import org.example.library.serviceInterfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/createProducts")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (null == product) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts(){

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if (product==null){
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (null == updatedProduct) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updatePartialProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updatePartialProduct(id, product);
        if (null == updatedProduct) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }


}
