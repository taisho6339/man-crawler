package com.taisho6339.man.crawler.controller;

import com.taisho6339.man.crawler.model.Book;
import com.taisho6339.man.crawler.model.BookJDBCComponent;
import com.taisho6339.man.crawler.model.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sakamohiroki on 2016/05/25.
 */
@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService service;

    @Autowired
    BookJDBCComponent bookjdbc;

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @ModelAttribute(value = "book")
    Book setUpForm() {
        //Formに紐付けるために、使うページを開くときに返す
        logger.error("call setUpForm");
        return new Book();
    }

    @RequestMapping(method = RequestMethod.GET)
    String list(Model model) {
        logger.error("call list");
        logger.error(model.getClass().getSimpleName());
        model.addAttribute("books", service.findAll());
        return "books/list";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    String create() {
        return "books/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(@Validated Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "books/create";
        }
        service.save(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    String update(@RequestParam Long id, Model model) {
        logger.error("id:" + id);
        logger.error("model:" + model.toString());
        model.addAttribute("book", service.findOne(id));
        return "books/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    String update(@RequestParam Long id, @Validated Book book, BindingResult result, Model model) {
        logger.error("id:" + id);
        logger.error("book:" + book.toString());
        logger.error("result:" + result.toString());
        logger.error("model:" + model.toString());

        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "update";
        }
        book.setId(id);
        service.update(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/books";
    }

    @RequestMapping(value = {"update", "create"}, params = "goToTop", method = RequestMethod.POST)
    String gotoTop() {
        return "redirect:/books";
    }

    //デフォルトのgetと区別するためにtitleパラメータをもつものとしてフィルタリングしている
    @RequestMapping(method = RequestMethod.GET, params = "title")
    String search(Model model, @RequestParam String title) {
        logger.error("reqparam:" + title);
        model.addAttribute("books", bookjdbc.searchByTitle(title));
        return "books/list";
    }
}
