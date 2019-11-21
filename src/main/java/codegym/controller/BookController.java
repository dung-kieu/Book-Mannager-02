package codegym.controller;

import codegym.model.Book;
import codegym.model.Category;
import codegym.service.BookService;
import codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categorys")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/books")
    public ModelAndView showListBooks(@RequestParam("s") Optional<String> s, @PageableDefault(size = 10) Pageable pageable) {
        Page<Book> books;
        if (s.isPresent()) {
            books = bookService.findAllByCategoryContaining(s.get(), s.get(), pageable);
        } else {
            books = bookService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/create-book")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create-book")
    public ModelAndView saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "New Book is create successfully!");
        return modelAndView;
    }

    @GetMapping("/edit-book/{id}")
    public ModelAndView showEditForm(@PathVariable("book") Long id) {
        Book book = bookService.findById(id);
        if (book != null) {
            ModelAndView modelAndView = new ModelAndView("/book/edit");
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-book")
    public ModelAndView updateBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/book/edit");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "Update this book successfully!");
        return modelAndView;
    }

    @GetMapping("/delete-book/{id}")
    public ModelAndView deleteForm(@PathVariable("edit") Long id) {
        Book book = bookService.findById(id);
        if (book != null) {
            ModelAndView modelAndView = new ModelAndView("/book/delete");
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-book")
    public String deleteBook(@ModelAttribute("book") Book book) {
        bookService.delete(book.getId());
        return "redirect:book";
    }

    @GetMapping("/view-book/{id}")
    public ModelAndView readBook(@PathVariable Long id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/book/view");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

}