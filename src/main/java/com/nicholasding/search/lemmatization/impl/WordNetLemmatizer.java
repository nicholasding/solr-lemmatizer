package com.nicholasding.search.lemmatization.impl;

import com.nicholasding.search.lemmatization.ExceptionList;
import com.nicholasding.search.lemmatization.Lemmatizer;
import com.nicholasding.search.lemmatization.POS;
import com.nicholasding.search.lemmatization.WordNetReader;
import com.nicholasding.search.util.RTrie;
import com.nicholasding.search.util.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28
 */
public class WordNetLemmatizer implements Lemmatizer {

    private Trie trie;
    private ExceptionList exceptionList;

    /**
     * Default constructor will load the required resources and construct the trie.
     *
     * @param reader
     */
    public WordNetLemmatizer(WordNetReader reader) {
        exceptionList = reader.readExceptionList();
        trie = new RTrie();
        for (String word : reader.readLemmas()) {
            trie.put(word, Boolean.TRUE);
        }
    }

    public String stem(String word, POS pos) {
        String exception = checkExceptionList(word, pos);
        if (exception != null) return exception;

        String[] candidates = transform(word, pos);
        for (String candidate : candidates) {
            if (trie.contains(candidate)) return candidate;
        }

        return word;
    }

    protected String[] transform(String word, POS pos) {
        List<String> candidates = new ArrayList<String>();
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

    protected String checkExceptionList(String word, POS pos) {
        return exceptionList.lookupException(word, pos);
    }

}
