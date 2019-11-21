package codegym.service.impl;

import codegym.model.Book;
import codegym.model.Category;
import codegym.repository.BookRepository;
import codegym.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;


    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Iterable<Book> findAllByCategory(Category category) {
        return null;
    }

    @Override
    public Page<Book> findAllByCategoryContaining(String category, String name, Pageable pageable) {
        return null;
    }
}