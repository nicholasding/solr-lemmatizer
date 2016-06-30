package com.nicholasding.search.lemmatization;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class ExceptionList {

    private Map<String, String> nounExc, verbExc, adjectiveExc, adverbExc;

    public ExceptionList() {}

    public void addExceptionList(POS pos, Map<String, String> map) {
        switch (pos) {
            case NOUN:
                if (nounExc == null) nounExc = new HashMap<>();
                nounExc.putAll(map);
                break;
            case ADJECTIVE:
                if (adjectiveExc == null) adjectiveExc = new HashMap<>();
                adjectiveExc.putAll(map);
                break;
            case VERB:
                if (verbExc == null) verbExc = new HashMap<>();
                verbExc.putAll(map);
                break;
            case ADVERB:
                if (adverbExc == null) adverbExc = new HashMap<>();
                adverbExc.putAll(map);
                break;
        }
    }

    public String lookupException(String word) {
        return lookupException(word, null);
    }

    public String lookupException(String word, POS pos) {
        if (pos == null) {
            String exc = lookupLists(word, nounExc, verbExc, adjectiveExc, adverbExc);
            if (exc != null) {
                return exc;
            }

            return null;
        }

        switch (pos) {
            case NOUN:      return lookupList(word, nounExc);
            case ADJECTIVE: return lookupList(word, adjectiveExc);
            case VERB:      return lookupList(word, verbExc);
            case ADVERB:    return lookupList(word, adverbExc);
        }

        return null;
    }

    private String lookupList(String word, Map<String, String> list) {
        if (list != null) {
            return list.get(word);
        }

        return null;
    }

    private String lookupLists(String word, Map<String, String>... lists) {
        for (Map<String, String> list : lists) {
            if (list != null && list.containsKey(word)) return list.get(word);
        }
        return null;
    }

}
