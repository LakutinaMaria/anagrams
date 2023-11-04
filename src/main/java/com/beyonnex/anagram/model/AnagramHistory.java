package com.beyonnex.anagram.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "anagram_history")
public class AnagramHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String baseWord;

    public AnagramHistory(String baseWord) {
        this.baseWord = baseWord;
    }

    public AnagramHistory() {

    }

    public AnagramHistory(Long id, String baseWord) {
        this.baseWord = baseWord;
        this.id = id;
    }
}
