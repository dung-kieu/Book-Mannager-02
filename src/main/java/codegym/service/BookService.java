package codegym.service;

import codegym.model.Book;
import codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    Book findById(Long id);

    void save(Book book);

    void delete(Long id);

    Iterable<Book> findAllByCategory(Category category);

    Page<Book> findAllByCategoryContaining(String category,String name, Pageable pageable);
}
