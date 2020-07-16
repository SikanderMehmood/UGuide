package Main.Scrap.Controller;


import Main.Scrap.Model.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api")
public class PersonController {

    public PersonController(){

    }
    @PostMapping("/new")
    public Person saveNewPerson(@Valid @RequestBody Person person)
    {
        return person;
    }
}
