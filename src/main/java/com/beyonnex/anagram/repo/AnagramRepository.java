package com.beyonnex.anagram.repo;

import com.beyonnex.anagram.model.Anagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AnagramRepository extends JpaRepository<Anagram, Long> {
    Optional<Set<Anagram>> findByAnagramHistoryId(Long id);
}
