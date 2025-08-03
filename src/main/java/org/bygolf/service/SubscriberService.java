package org.bygolf.service;

import org.bygolf.model.Subscriber;
import org.bygolf.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    public List<Subscriber> getSubscribers() {
        return subscriberRepository.findAll();
    }

    public Subscriber addSubscriber(Subscriber subscriber) {
        return  subscriberRepository.save(subscriber);
    }

}
