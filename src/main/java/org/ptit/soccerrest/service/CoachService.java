package org.ptit.soccerrest.service;

import java.util.List;
import java.util.Optional;

import org.ptit.soccerrest.domain.model.Coach;
import org.ptit.soccerrest.domain.repository.CoachRepository;
import org.springframework.stereotype.Service;

@Service
public class CoachService {
    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Optional<Coach> findById(Long id) {
        return coachRepository.findById(id);
    }

    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    public Optional<Coach> update(Long id, Coach coach) {
        return coachRepository.findById(id).map(existing -> {
            existing.setName(coach.getName());
            existing.setAge(coach.getAge());
            existing.setNationality(coach.getNationality());
            existing.setLicenseLevel(coach.getLicenseLevel());
            existing.setExperienceYears(coach.getExperienceYears());
            existing.setClubName(coach.getClubName());
            existing.setPreferredFormation(coach.getPreferredFormation());
            existing.setActive(coach.getActive());
            return coachRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        return coachRepository.findById(id).map(c -> {
            coachRepository.delete(c);
            return true;
        }).orElse(false);
    }
}

