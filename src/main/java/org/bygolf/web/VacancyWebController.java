package org.bygolf.web;

import org.bygolf.model.Subscriber;
import org.bygolf.model.Vacancy;
import org.bygolf.service.SubscriberService;
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
    SubscriberService subscriberService;

    public VacancyWebController(VacancyService vacancyService, SubscriberService subscriberService) {
        this.vacancyService = vacancyService;
        this.subscriberService = subscriberService;
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

    @GetMapping("/subscriber")
    public List<Subscriber> getSubscribers() {
        return subscriberService.getSubscribers();
    }

    @PutMapping("/subscriber")
    public Subscriber addSubscriber(Subscriber subscriber) {
        return subscriberService.addSubscriber(subscriber);
    }

}
