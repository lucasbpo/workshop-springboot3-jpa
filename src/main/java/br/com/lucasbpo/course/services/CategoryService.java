package br.com.lucasbpo.course.services;

import br.com.lucasbpo.course.entities.Category;
import br.com.lucasbpo.course.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository CategoryRepository) {
        this.categoryRepository = CategoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
