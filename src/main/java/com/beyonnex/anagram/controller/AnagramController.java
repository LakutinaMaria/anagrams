package com.beyonnex.anagram.controller;

import com.beyonnex.anagram.service.AnagramService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@Slf4j
public class AnagramController {

    private AnagramService anagramService;

    @GetMapping("/areAnagrams")
    public boolean areAnagrams(@RequestParam(required = false) String word1,
                               @RequestParam(required = false) String word2) {
        log.info("AnagramController: Received request to check if {} and {} are anagrams.", word1, word2);
        boolean result = anagramService.areAnagrams(word1, word2);
        log.info("AnagramController: {} and {} are anagrams: {}", word1, word2, result);
        return result;

    }

    @GetMapping("/getAllAnagrams")
    public Set<String> getAllAnagrams(@RequestParam(required = false) String word) {
        log.info("AnagramController: Received request to get all anagrams of {}.", word);
        Set<String> anagrams = anagramService.getAllAnagrams(word);
        log.info("AnagramController: Anagrams for {}: {}", word, anagrams);
        return anagrams;
    }
}
