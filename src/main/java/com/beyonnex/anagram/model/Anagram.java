package com.beyonnex.anagram.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Anagram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String anagram;

    @ManyToOne
    @JoinColumn(name = "anagram_history_id")
    private AnagramHistory anagramHistory;

    public Anagram(String anagram, AnagramHistory anagramHistory) {
        this.anagram = anagram;
        this.anagramHistory = anagramHistory;
    }

    public Anagram() {

    }
}
