package codegym.controller;

import codegym.model.Category;
import codegym.repository.CategoryRepository;
import codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class RestCategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/restCategory/" , method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> ListAllCategory(){
        Iterable<Category> categories = categoryService.findAll();
        if (categories == null){
            return new ResponseEntity<Iterable<Category>>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<Iterable<Category>>(categories,HttpStatus.OK);
    }

    @RequestMapping(value = "/restCategory/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id){
        System.out.println("Fetching Category with id" +id);
        Category category = categoryService.findById(id);
        if (category == null){
            System.out.println("Category with id" +id+"not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/restCategory/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Category " + category.getName());
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customers/{id}").buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/restCategory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCustomer(@PathVariable("id") long id, @RequestBody Category category) {
        System.out.println("Updating Category " + id);

        Category currentCategory = categoryService.findById(id);

        if (currentCategory == null) {
            System.out.println("Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        currentCategory.setName(category.getName());
        currentCategory.setDescription(category.getDescription());
        currentCategory.setId(category.getId());

        categoryService.save(currentCategory);
        return new ResponseEntity<Category>(currentCategory, HttpStatus.OK);
    }

    @RequestMapping(value = "/restCategory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteCustomer(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Category with id " + id);

        Category category = categoryService.findById(id);
        if (category == null) {
            System.out.println("Unable to delete.Category with id " + id + " not found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }

        categoryService.delete(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

}
