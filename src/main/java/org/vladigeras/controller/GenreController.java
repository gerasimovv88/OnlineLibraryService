package org.vladigeras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladigeras.model.GenreEntity;
import org.vladigeras.service.GenreService;

import java.util.List;
import java.util.regex.Matcher;
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
        return isValidTitle(title) && genreService.save(title);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "title", required = true) String title) {
        return isValidTitle(title) && genreService.delete(title);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "oldTitle", required = true) String oldTitle,
                          @RequestParam(name = "newTitle", required = true) String newTitle) {
        return isValidTitle(oldTitle) && isValidTitle(newTitle) && genreService.update(oldTitle, newTitle);
    }

    private boolean isValidTitle(String title) {
        if (title != null) {
            Matcher matcher = titlePattern.matcher(title);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
