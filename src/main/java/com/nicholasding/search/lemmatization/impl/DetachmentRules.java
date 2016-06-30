package com.nicholasding.search.lemmatization.impl;

import com.nicholasding.search.lemmatization.POS;

import java.util.ArrayList;
import java.util.List;

/**
 * Detachment Rules in WordNet's morphological processing.
 *
 * @see <a href="https://wordnet.princeton.edu/wordnet/man/morphy.7WN.html">morphy (7WN)</a>
 *
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class DetachmentRules {
    public String[] apply(String word, POS pos) {
        List<String> candidates = new ArrayList<>();
        switch (pos) {
            case NOUN:
                if (word.endsWith("s")) {
                    candidates.add(word.substring(0, word.length() - 1));
                }
                if (word.endsWith("ses")) {
                    candidates.add(word.substring(0, word.length() - 2));
                    break;
                }
                if (word.endsWith("xes")) {
                    candidates.add(word.substring(0, word.length() - 2));
                    break;
                }
                if (word.endsWith("zes")) {
                    candidates.add(word.substring(0, word.length() - 2));
                    break;
                }
                if (word.endsWith("ches")) {
                    candidates.add(word.substring(0, word.length() - 2));
                    break;
                }
                if (word.endsWith("shes")) {
                    candidates.add(word.substring(0, word.length() - 2));
                    break;
                }
                if (word.endsWith("men")) {
                    candidates.add(word.substring(0, word.length() - 3) + "man");
                    break;
                }
                if (word.endsWith("ies")) {
                    candidates.add(word.substring(0, word.length() - 3) + "y");
                    break;
                }
            case VERB:
                if (word.endsWith("s")) {
                    candidates.add(word.substring(0, word.length() - 1));
                }
                if (word.endsWith("ies")) {
                    candidates.add(word.substring(0, word.length() - 3) + "y");
                }
                if (word.endsWith("es")) {
                    candidates.add(word.substring(0, word.length() - 1));
                    candidates.add(word);
                }
                if (word.endsWith("ed")) {
                    candidates.add(word.substring(0, word.length() - 1));
                    candidates.add(word);
                    break;
                }
                if (word.endsWith("ing")) {
                    candidates.add(word.substring(0, word.length() - 3) + "e");
                    candidates.add(word);
                    break;
                }
                break;
            case ADJECTIVE:
                if (word.endsWith("er") || word.endsWith("est")) {
                    candidates.add(word);
                }
                if (word.endsWith("er")) {
                    candidates.add(word.substring(0, word.length() - 1));
                }
                if (word.endsWith("est")) {
                    candidates.add(word.substring(0, word.length() - 2));
                }
                break;
            default:
                candidates.add(word);
        }

        return candidates.toArray(new String[0]);
    }
}
