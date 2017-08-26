package org.vladigeras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vladigeras.model.BookEntity;
import org.vladigeras.service.BookService;
import org.vladigeras.util.ValidationPatterns;
import org.vladigeras.util.ValueValidator;

import java.util.List;

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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List searchBooks(@RequestParam(name = "title", required = false) String title,
                                        @RequestParam(name = "author", required = false) String author,
                                        @RequestParam(name = "genre", required = false) String genre) {
        if (!ValueValidator.isValidValueOnPattern(title, ValidationPatterns.TITLE_PATTERN)) title = "";
        if (!ValueValidator.isValidValueOnPattern(author, ValidationPatterns.AUTHOR_PATTERN)) author = "";
        if (!ValueValidator.isValidValueOnPattern(genre, ValidationPatterns.GENRE_PATTERN)) genre = "";
        return bookService.search(title, author, genre);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public BookEntity getBookById(@RequestParam(name = "id", required = true) Long id) {
        return ValueValidator.isValidId(id) ? bookService.getBookById(id) : null;
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public boolean save(@RequestBody BookEntity book) {
//        return bookService.save(book);
//    }
}
