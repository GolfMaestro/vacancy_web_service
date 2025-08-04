package org.bygolf.service;

import org.apache.camel.ProducerTemplate;
import org.bygolf.model.Subscriber;
import org.bygolf.model.Vacancy;
import org.bygolf.repository.SubscriberRepository;
import org.bygolf.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VacancyEmailService {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Transactional
    @Scheduled(cron = "0 0 10 * * ?")
    public void sendDailyVacancyEmails() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        List<Subscriber> subscribers = subscriberRepository.findAll();

        for (Subscriber subscriber : subscribers) {
            for (Vacancy vacancy : vacancies) {
                if (isMatch(subscriber, vacancy)) {
                    String emailContent = buildEmailContent(subscriber, vacancy);
                    producerTemplate.sendBodyAndHeader(
                            "direct:sendEmail",
                            emailContent,
                            "toEmail",
                            subscriber.getEmail()
                    );
                }
            }
        }
    }

    private boolean isMatch(Subscriber subscriber, Vacancy vacancy) {
        return subscriber.getCity().equalsIgnoreCase(vacancy.getCity()) &&
                subscriber.getPosition().equalsIgnoreCase(vacancy.getPosition());
    }

    private String buildEmailContent(Subscriber subscriber, Vacancy vacancy) {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);

        String answerEmail = String.format("""
            Здравствуйте %s,
            
            Информируем вас о новой вакансии на должность «%s».
            
            Наименование: «%s»
            Описание: «%s»
            Уровень зарплаты: «%s»
            Требуемый опыт работы: «%s»
            
            С уважением,
            Цифровое Будущее
            %s
            """,
                subscriber.getName(),
                vacancy.getPosition(),
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getSalary(),
                vacancy.getExperience(),
                formattedDate
        );

        return answerEmail;
    }
}
