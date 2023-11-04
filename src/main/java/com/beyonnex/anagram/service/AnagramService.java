package com.beyonnex.anagram.service;

import com.beyonnex.anagram.model.Anagram;
import com.beyonnex.anagram.model.AnagramHistory;
import com.beyonnex.anagram.repo.AnagramHistoryRepository;
import com.beyonnex.anagram.repo.AnagramRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AnagramService {

    private AnagramRepository anagramRepository;
    private AnagramHistoryRepository anagramHistoryRepository;

    public boolean areAnagrams(String word1, String word2) {
        String sortedWord1 = sortAndNormalize(word1);
        String sortedWord2 = sortAndNormalize(word2);

        if (sortedWord1.equals(sortedWord2)) {
            log.info("AnagramService: {} and {} are anagrams.", word1, word2);
            createOrUpdateAnagramHistory(word1, word2);
            return true;
        } else {
            log.info("AnagramService: {} and {} are not anagrams.", word1, word2);
            return false;
        }
    }

    public Set<String> getAllAnagrams(String word) {
        String sortedWord = sortAndNormalize(word);
        Optional<AnagramHistory> anagramHistory = anagramHistoryRepository.findByBaseWord(sortedWord);
        if (anagramHistory.isEmpty()) {
            log.info("AnagramService: No anagrams found for {}.", word);
            return Collections.emptySet();
        } else {
            Set<String> anagrams = getAnagrams(anagramHistory.get());
            anagrams.remove(word);
            log.info("AnagramService: Anagrams for {}: {}.", word, anagrams);
            return anagrams;
        }
    }

    private String sortAndNormalize(String word) {
        return Arrays.stream(word.replaceAll("\\s", "").toLowerCase().split(""))
                .sorted()
                .collect(Collectors.joining());
    }

    private void createOrUpdateAnagramHistory(String word1, String word2) {
        String sortedWord = sortString(word1);
        AnagramHistory anagramHistory = getOrCreateAnagramHistory(sortedWord);

        Set<String> anagrams = getAnagrams(anagramHistory);

        saveIfNotExists(anagrams, word1, anagramHistory);
        saveIfNotExists(anagrams, word2, anagramHistory);
    }

    private String sortString(String word) {
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    private AnagramHistory getOrCreateAnagramHistory(String sortedWord) {
        return anagramHistoryRepository.findByBaseWord(sortedWord)
                .orElseGet(() -> anagramHistoryRepository.save(new AnagramHistory(sortedWord)));
    }

    private Set<String> getAnagrams(AnagramHistory anagramHistory) {
        return anagramRepository.findByAnagramHistoryId(anagramHistory.getId())
                .map(anagrams -> anagrams.stream()
                        .map(Anagram::getAnagram)
                        .collect(Collectors.toSet()))
                .orElseGet(HashSet::new);
    }

    private void saveIfNotExists(Set<String> anagrams, String word, AnagramHistory anagramHistory) {
        if (!anagrams.contains(word)) {
            anagramRepository.save(new Anagram(word, anagramHistory));
        }
    }


}
