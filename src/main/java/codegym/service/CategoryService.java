package codegym.service;

import codegym.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void delete(Long id);
}
