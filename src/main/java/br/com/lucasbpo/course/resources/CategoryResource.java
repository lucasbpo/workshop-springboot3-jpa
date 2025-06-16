package br.com.lucasbpo.course.resources;

import br.com.lucasbpo.course.entities.Category;
import br.com.lucasbpo.course.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryResource {

    private CategoryService service;

    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        var Categories = service.findAll();
        return ResponseEntity.ok(Categories);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        var Category = service.findById(id);
        return ResponseEntity.ok(Category);
    }
}
