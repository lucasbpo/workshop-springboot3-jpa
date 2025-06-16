package br.com.lucasbpo.course.services;

import br.com.lucasbpo.course.entities.Category;
import br.com.lucasbpo.course.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository CategoryRepository;

    public CategoryService(CategoryRepository CategoryRepository) {
        this.CategoryRepository = CategoryRepository;
    }

    public List<Category> findAll() {
        return CategoryRepository.findAll();
    }

    public Category findById(Long id) {
        return CategoryRepository.findById(id).get();
    }
}
