package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Book;
import com.taisho6339.man.crawler.model.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/25.
 */
@RestController
@RequestMapping("api/books")
public class BookAPIController {

    @Autowired
    BookService service;

    @RequestMapping(method = RequestMethod.GET)
    List<Book> getBooks() {
        return service.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Book getBook(@PathVariable Long id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Book postBook(@RequestBody Book book) {
        return service.save(book);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    Book postBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return service.update(book);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(@PathVariable Long id) {
        service.delete(id);
    }
}
