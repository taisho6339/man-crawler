package com.taisho6339.man.crawler.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/25.
 */
@Component
@Transactional
public class BookService {
    @Autowired
    BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findOne(Long id) {
        return repository.findOne(id);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public Book update(Book book) {
        if (repository.findOne(book.getId()) != null) {
            return repository.save(book);
        }
        return book;
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
