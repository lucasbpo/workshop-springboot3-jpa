package br.com.lucasbpo.course.resources;

import br.com.lucasbpo.course.entities.Product;
import br.com.lucasbpo.course.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductResource {

    private ProductService service;

    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        var Categories = service.findAll();
        return ResponseEntity.ok(Categories);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        var Product = service.findById(id);
        return ResponseEntity.ok(Product);
    }
}
