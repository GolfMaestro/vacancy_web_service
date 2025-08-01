package org.bygolf.service;

import org.bygolf.model.Candidate;
import org.bygolf.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate addCandidate(Candidate candidate) {
        return  candidateRepository.save(candidate);
    }

}
