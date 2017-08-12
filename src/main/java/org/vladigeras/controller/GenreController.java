package org.vladigeras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladigeras.controller.Util.ValueValidator;
import org.vladigeras.model.GenreEntity;
import org.vladigeras.service.GenreService;

import java.util.List;
import java.util.regex.Pattern;


@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    private static final Pattern titlePattern = Pattern.compile("[а-яёА-ЯЁ\\s]+");  //Russian literal + space

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<GenreEntity> getAllGenres() {
        List<GenreEntity> result;
        result = genreService.getAllGenres();
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean save(@RequestParam(name = "title", required = true) String title) {
        return ValueValidator.isValidValueOnPattern(title, titlePattern) && genreService.save(title);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "title", required = true) String title) {
        return ValueValidator.isValidValueOnPattern(title, titlePattern) && genreService.delete(title);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "oldTitle", required = true) String oldTitle,
                          @RequestParam(name = "newTitle", required = true) String newTitle) {
        return ValueValidator.isValidValueOnPattern(oldTitle, titlePattern)
                && ValueValidator.isValidValueOnPattern(newTitle, titlePattern)
                && genreService.update(oldTitle, newTitle);
    }
}
