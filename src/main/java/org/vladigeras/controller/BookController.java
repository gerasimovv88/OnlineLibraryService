package org.vladigeras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vladigeras.model.BookEntity;
import org.vladigeras.service.BookService;
import org.vladigeras.util.ValueValidator;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "id", required = true) Long id) {
        return ValueValidator.isValidId(id) && bookService.delete(id);
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public boolean save(@RequestBody BookEntity book) {
//        return bookService.save(book);
//    }
}
