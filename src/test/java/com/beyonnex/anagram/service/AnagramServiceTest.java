package com.beyonnex.anagram.service;

import com.beyonnex.anagram.model.Anagram;
import com.beyonnex.anagram.model.AnagramHistory;
import com.beyonnex.anagram.repo.AnagramHistoryRepository;
import com.beyonnex.anagram.repo.AnagramRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class AnagramServiceTest {

    @Mock
    private AnagramRepository anagramRepository;

    @Mock
    private AnagramHistoryRepository anagramHistoryRepository;

    @InjectMocks
    private AnagramService underTest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Two words are anagrams")
    public void testAreAnagrams_WhenWordsAreAnagrams_ExpectTrue() {
        // Given
        String word1 = "listen";
        String word2 = "silent";
        String baseWord = "eilnst";
        AnagramHistory anagramHistory = new AnagramHistory(1L, baseWord);

        given(anagramHistoryRepository.findByBaseWord(baseWord))
                .willReturn(Optional.of(anagramHistory));
        // When
        boolean result = underTest.areAnagrams(word1, word2);
        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Two words are NOT anagrams")
    public void testAreAnagrams_WhenWordsAreNotAnagrams_ExpectFalse() {
        // Given
        String word1 = "hello";
        String word2 = "world";
        // When
        boolean result = underTest.areAnagrams(word1, word2);
        // Then
        assertFalse(result);

    }

    @Test
    @DisplayName("Two identical words are anagrams")
    public void testAreAnagramsWhenWordsAreTheSameExpectTrue() {
        // Given
        String word1 = "listen";
        String word2 = "listen";
        String baseWord = "eilnst";
        AnagramHistory anagramHistory = new AnagramHistory(1L, baseWord);

        given(anagramHistoryRepository.findByBaseWord(baseWord))
                .willReturn(Optional.of(anagramHistory));
        // When
        boolean result = underTest.areAnagrams(word1, word2);
        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Test GetAllAnagrams. Expect to return 1 anagram")
    public void testGetAllAnagrams_WhenAnagramHistoryExists_ExpectAnagramsReturned() {
        // Given
        String word = "hello";
        String sortedWord = "ehllo";

        AnagramHistory anagramHistory = new AnagramHistory(1L, sortedWord);

        Set<Anagram> anagramSet = new HashSet<>();
        anagramSet.add(new Anagram("olleh", anagramHistory));

        given(anagramHistoryRepository.findByBaseWord(sortedWord)).willReturn(Optional.of(anagramHistory));
        given(anagramRepository.findByAnagramHistoryId(1L)).willReturn(Optional.of(anagramSet));
        // When
        Set<String> anagrams = underTest.getAllAnagrams(word);
        // Then
        assertEquals(1, anagrams.size());
        assertTrue(anagrams.contains("olleh"));
    }

    @Test
    @DisplayName("Test GetAllAnagrams. Expect to return empty list")
    public void testGetAllAnagrams_WhenNoAnagramHistoryExists_ExpectEmptySet() {
        // Given
        String word = "test";

        given(anagramHistoryRepository.findByBaseWord(any())).willReturn(Optional.empty());
        // When
        Set<String> anagrams = underTest.getAllAnagrams(word);
        // Then
        assertTrue(anagrams.isEmpty());
    }
}