package org.bygolf.web;

import org.bygolf.model.Vacancy;
import org.bygolf.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VacancyWebController {

    @Autowired
    VacancyService vacancyService;

    public VacancyWebController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping("/")
    public String helloPage() {
        return "Vacancy web app";
    }

    @GetMapping("/vacancy")
    public List<Vacancy> getVacancies() {
        return vacancyService.getVacancies();
    }

    @PostMapping("/vacancy")
    public Vacancy addVacancy(@Valid Vacancy vacancy) {
        return vacancyService.addVacancy(vacancy);
    }

}
