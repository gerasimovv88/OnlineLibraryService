package org.vladigeras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vladigeras.controller.Util.ValueValidator;
import org.vladigeras.model.AuthorEntity;
import org.vladigeras.service.AuthorService;

import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    private static final Pattern authorPattern = Pattern.compile("[а-яёА-ЯЁ\\-]+");  //Russian literal + '-'

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<AuthorEntity> getAllAuthors() {
        List<AuthorEntity> result;
        result = authorService.getAllAuthors();
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public boolean save(@RequestParam(name = "firstname", required = true) String firstname,
                    @RequestParam(name = "middlename", required = true) String middlename,
                    @RequestParam(name = "lastname", required = true) String lastname) {

        List<String> params = new ArrayList<>();
        params.add(firstname);
        params.add(middlename);
        params.add(lastname);

        if (ValueValidator.isValidValueOnPattern(params, authorPattern)){
            String fio = generateAuthorFio(firstname, middlename, lastname);
            AuthorEntity author = new AuthorEntity(firstname, middlename, lastname, fio);
            return authorService.save(author);
        }
        else return false;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(@RequestParam(name = "id", required = true) Long id) {
        return ValueValidator.isValidId(id) && authorService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean save(@RequestParam(name = "id", required = true) Long id,
                        @RequestParam(name = "firstname", required = false) String firstname,
                        @RequestParam(name = "middlename", required = false) String middlename,
                        @RequestParam(name = "lastname", required = false) String lastname) {
        List<String> params = new ArrayList<>();
        params.add(firstname);
        params.add(middlename);
        params.add(lastname);
        if (ValueValidator.isValidValueOnPattern(params, authorPattern) && ValueValidator.isValidId(id)){
            String fio = generateAuthorFio(firstname, middlename, lastname);
            AuthorEntity author = new AuthorEntity(firstname, middlename, lastname, fio);
            author.setId(id);
            return authorService.update(author);
        }
        else return false;
    }

    private String generateAuthorFio(String firstname, String middlename, String lastname) {
        return lastname + " " + firstname.charAt(0) + "." + middlename.charAt(0) + ".";
    }

}
