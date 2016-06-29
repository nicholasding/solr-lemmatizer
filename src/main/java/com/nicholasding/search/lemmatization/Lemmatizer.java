package com.nicholasding.search.lemmatization;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28
 */
public interface Lemmatizer {

    /**
     * It tries to find the basic form (lemma) for a given word.
     *
     * @param word
     * @return the original form of the word
     */
    String stem(String word, POS pos);

}
