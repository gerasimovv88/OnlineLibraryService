package org.vladigeras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladigeras.model.GenreEntity;
import org.vladigeras.util.ValidationPatterns;
import org.vladigeras.util.ValueValidator;
import org.vladigeras.service.GenreService;

import java.util.List;


@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<GenreEntity> getAllGenres() {
        List<GenreEntity> result;
        result = genreService.getAllGenres();
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean save(@RequestParam(name = "title", required = true) String title) {
        return ValueValidator.isValidValueOnPattern(title, ValidationPatterns.GENRE_PATTERN) && genreService.save(title);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "title", required = true) String title) {
        return ValueValidator.isValidValueOnPattern(title, ValidationPatterns.GENRE_PATTERN) && genreService.delete(title);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "oldTitle", required = true) String oldTitle,
                          @RequestParam(name = "newTitle", required = true) String newTitle) {
        return ValueValidator.isValidValueOnPattern(oldTitle, ValidationPatterns.GENRE_PATTERN)
                && ValueValidator.isValidValueOnPattern(newTitle, ValidationPatterns.GENRE_PATTERN)
                && genreService.update(oldTitle, newTitle);
    }
}
