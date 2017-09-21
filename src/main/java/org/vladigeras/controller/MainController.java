package org.vladigeras.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vladigeras.model.BookEntity;
import org.vladigeras.service.AuthorService;
import org.vladigeras.service.BookService;
import org.vladigeras.service.GenreService;
import org.vladigeras.util.ValueValidator;

/**
 * Created by vladi_geras on 05.09.17.
 */

@Controller
@RequestMapping(value = "/")
public class MainController {

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookService bookService;

    @Autowired
    public MainController(AuthorService authorService, GenreService genreService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "index";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String showBook(Model model,
                           @RequestParam(name = "id", required = true) Long id) {
        if (ValueValidator.isValidId(id)) {
            BookEntity bookEntity = bookService.getBookEntityById(id);
            ObjectMapper objectMapper = new ObjectMapper();
            model.addAttribute("book", bookService.getBookById(id));
            try {
                model.addAttribute("image", objectMapper.writeValueAsString(bookEntity.getImage()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }
        return "book";
    }
}
