package org.ptit.soccerrest.domain.repository;

import org.ptit.soccerrest.domain.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}

