package org.ptit.soccerrest.service;

import java.util.List;
import java.util.Optional;

import org.ptit.soccerrest.domain.model.Player;
import org.ptit.soccerrest.domain.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public Optional<Player> update(Long id, Player player) {
        return playerRepository.findById(id).map(existing -> {
            existing.setName(player.getName());
            existing.setAge(player.getAge());
            existing.setNationality(player.getNationality());
            existing.setPosition(player.getPosition());
            existing.setJerseyNumber(player.getJerseyNumber());
            existing.setClubName(player.getClubName());
            existing.setPreferredFoot(player.getPreferredFoot());
            existing.setMarketValue(player.getMarketValue());
            existing.setActive(player.getActive());
            return playerRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        return playerRepository.findById(id).map(player -> {
            playerRepository.delete(player);
            return true;
        }).orElse(false);
    }
}

