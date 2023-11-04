package com.beyonnex.anagram.repo;

import com.beyonnex.anagram.model.AnagramHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnagramHistoryRepository extends JpaRepository<AnagramHistory, Long> {
    Optional<AnagramHistory> findByBaseWord(String baseWord);
}
