package com.nicholasding.search.lemmatization;

import java.util.Map;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class ExceptionList {

    Map<String, String> nounExc, verbExc, adjectiveExc, adverbExc;

    public ExceptionList() {}

    public void addExceptionList(POS pos, Map<String, String> map) {
        switch (pos) {
            case NOUN:
                nounExc = map;
                break;
            case ADJECTIVE:
                adjectiveExc = map;
                break;
            case VERB:
                verbExc = map;
                break;
            case ADVERB:
                adverbExc = map;
                break;
        }
    }

    public String lookupException(String word) {
        return lookupException(word, null);
    }

    public String lookupException(String word, POS pos) {
        if (pos == null) {
            String exc = nounExc.get(word);
            if (exc != null) return exc;
            exc = verbExc.get(word);
            if (exc != null) return exc;
            exc = adjectiveExc.get(word);
            if (exc != null) return exc;
            exc = adverbExc.get(word);
            if (exc != null) return exc;
        } else {
            switch (pos) {
                case NOUN:
                    return nounExc.get(word);
                case ADJECTIVE:
                    return adjectiveExc.get(word);
                case VERB:
                    return verbExc.get(word);
                case ADVERB:
                    return adverbExc.get(word);
            }
        }

        return null;
    }

}
