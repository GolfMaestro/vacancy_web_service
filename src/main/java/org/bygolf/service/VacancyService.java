package org.bygolf.service;

import org.bygolf.model.Vacancy;
import org.bygolf.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService {

    @Autowired
    VacancyRepository vacancyRepository;

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public Vacancy addVacancy(Vacancy vacancy) {
        Vacancy savedVacancy = vacancyRepository.save(vacancy);
        return savedVacancy;
    }

    public List<Vacancy> searchVacancyByFilter(String name, String city, String position) {
        return vacancyRepository.findVacancyByFilter(name, city, position);
    }

}
