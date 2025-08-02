package org.bygolf.web;

import org.bygolf.model.Candidate;
import org.bygolf.model.Vacancy;
import org.bygolf.service.CandidateService;
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

    @Autowired
    CandidateService candidateService;

    public VacancyWebController(VacancyService vacancyService, CandidateService candidateService) {
        this.vacancyService = vacancyService;
        this.candidateService = candidateService;
    }

    @GetMapping("/")
    public String helloPage() {
        return "Vacancy web app";
    }

    @GetMapping("/vacancy")
    public List<Vacancy> getVacancies(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String city,
                                      @RequestParam(required = false) String position) {
        return vacancyService.searchVacancyByFilter(name, city, position);
    }

    @PutMapping("/vacancy")
    public Vacancy addVacancy(@Valid Vacancy vacancy) {
        return vacancyService.addVacancy(vacancy);
    }

    @GetMapping("/candidate")
    public List<Candidate> getCandidates() {
        return candidateService.getCandidates();
    }

    @PutMapping("/candidate")
    public Candidate addCandidate(Candidate candidate) {
        return candidateService.addCandidate(candidate);
    }

}
