package codegym.repository;

import codegym.model.Book;
import codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Iterable<Book> findAllByCategory(Category category);
    Page<Book> findAllByCategoryAndName(String category, String name, Pageable pageable);
}
