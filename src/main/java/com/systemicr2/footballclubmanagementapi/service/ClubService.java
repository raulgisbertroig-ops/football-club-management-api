package com.systemicr2.footballclubmanagementapi.service;

import com.systemicr2.footballclubmanagementapi.model.Club;
import com.systemicr2.footballclubmanagementapi.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club no encontrado con ID: " + id));
    }
}