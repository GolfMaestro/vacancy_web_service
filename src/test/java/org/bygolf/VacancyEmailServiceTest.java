package org.bygolf;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.bygolf.model.Subscriber;
import org.bygolf.model.Vacancy;
import org.bygolf.repository.SubscriberRepository;
import org.bygolf.repository.VacancyRepository;
import org.bygolf.service.VacancyEmailService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import javax.mail.internet.MimeMessage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class VacancyEmailServiceTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Autowired
    private VacancyEmailService vacancyEmailService;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private CamelContext camelContext;

    private GreenMail greenMail;

    @BeforeEach
    public void setUp() throws Exception {

        greenMail = new GreenMail(ServerSetupTest.SMTP);
        greenMail.setUser("testuser", "testpass");
        greenMail.start();

        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:sendEmail")
                        .setHeader("subject", constant("Уведомление о новых вакансиях"))
                        .setHeader("from", constant("testuser@localhost"))
                        .setHeader("to", simple("${header.toEmail}"))
                        .to("smtp://localhost:" + ServerSetupTest.SMTP.getPort() + "?username=testuser&password=testpass")
                        .end();
            }
        });

        Vacancy vacancy = new Vacancy();
        vacancy.setName("Software Engineer");
        vacancy.setPosition("Developer");
        vacancy.setCity("Moscow");
        vacancy.setDescription("Java Developer Role");
        vacancy.setSalary(100000);
        vacancy.setExperience("3-5 years");
        vacancyRepository.save(vacancy);

        Subscriber subscriber = new Subscriber();
        subscriber.setName("John Doe");
        subscriber.setEmail("john.doe@example.com");
        subscriber.setCity("Moscow");
        subscriber.setPosition("Developer");
        subscriberRepository.save(subscriber);
    }

    @AfterEach
    public void tearDown() {
        greenMail.stop();
        vacancyRepository.deleteAll();
        subscriberRepository.deleteAll();
    }

    @Test
    public void testSendDailyVacancyEmails() throws Exception {
        vacancyEmailService.sendDailyVacancyEmails();
        greenMail.waitForIncomingEmail(5000, 1);

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertEquals(1, receivedMessages.length);

        MimeMessage email = receivedMessages[0];

        String sender = email.getFrom()[0].toString();
        String receiver = email.getAllRecipients()[0].toString();
        String subject = email.getSubject();

        assertEquals("testuser@localhost", sender);
        assertEquals("john.doe@example.com", receiver);
        assertEquals("Уведомление о новых вакансиях", subject);
    }
}