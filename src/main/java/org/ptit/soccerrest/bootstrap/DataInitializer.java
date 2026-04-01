package org.ptit.soccerrest.bootstrap;

import jakarta.annotation.PostConstruct;
import org.ptit.soccerrest.domain.model.Coach;
import org.ptit.soccerrest.domain.model.Player;
import org.ptit.soccerrest.domain.repository.CoachRepository;
import org.ptit.soccerrest.domain.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final CoachRepository coachRepository;
    private final PlayerRepository playerRepository;

    public DataInitializer(CoachRepository coachRepository, PlayerRepository playerRepository) {
        this.coachRepository = coachRepository;
        this.playerRepository = playerRepository;
    }

    @PostConstruct
    public void initializeData() {
        seedCoachesIfEmpty();
        seedPlayersIfEmpty();
    }

    private void seedCoachesIfEmpty() {
        if (coachRepository.count() > 0) {
            log.info("Coaches already exist, skipping sample data initialization.");
            return;
        }

        List<Coach> coaches = List.of(
                createCoach("Pep Guardiola", 53, "Spain", "UEFA Pro", 16, "Manchester City", "4-3-3"),
                createCoach("Carlo Ancelotti", 65, "Italy", "FIFA Pro", 29, "Real Madrid", "4-4-2"),
                createCoach("Mikel Arteta", 43, "Spain", "UEFA Elite", 6, "Arsenal", "4-3-3")
        );

        coachRepository.saveAll(coaches);
        log.info("Inserted {} sample coaches.", coaches.size());
    }

    private void seedPlayersIfEmpty() {
        if (playerRepository.count() > 0) {
            log.info("Players already exist, skipping sample data initialization.");
            return;
        }

        List<Player> players = List.of(
                createPlayer("Erling Haaland", 24, "Norway", "Striker", 9, "Manchester City", "Left", new BigDecimal("180000000.00")),
                createPlayer("Jude Bellingham", 21, "England", "Midfielder", 5, "Real Madrid", "Right", new BigDecimal("150000000.00")),
                createPlayer("Bukayo Saka", 23, "England", "Winger", 7, "Arsenal", "Left", new BigDecimal("140000000.00"))
        );

        playerRepository.saveAll(players);
        log.info("Inserted {} sample players.", players.size());
    }

    private Coach createCoach(String name,
                              Integer age,
                              String nationality,
                              String licenseLevel,
                              Integer experienceYears,
                              String clubName,
                              String preferredFormation) {
        Coach coach = new Coach();
        coach.setName(name);
        coach.setAge(age);
        coach.setNationality(nationality);
        coach.setLicenseLevel(licenseLevel);
        coach.setExperienceYears(experienceYears);
        coach.setClubName(clubName);
        coach.setPreferredFormation(preferredFormation);
        coach.setActive(Boolean.TRUE);
        return coach;
    }

    private Player createPlayer(String name,
                                Integer age,
                                String nationality,
                                String position,
                                Integer jerseyNumber,
                                String clubName,
                                String preferredFoot,
                                BigDecimal marketValue) {
        Player player = new Player();
        player.setName(name);
        player.setAge(age);
        player.setNationality(nationality);
        player.setPosition(position);
        player.setJerseyNumber(jerseyNumber);
        player.setClubName(clubName);
        player.setPreferredFoot(preferredFoot);
        player.setMarketValue(marketValue);
        player.setActive(Boolean.TRUE);
        return player;
    }
}


