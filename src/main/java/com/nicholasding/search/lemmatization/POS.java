package com.nicholasding.search.lemmatization;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28.
 */
public enum POS {

    NOUN(0),
    VERB(1),
    ADJECTIVE(2),
    ADVERB(3);

    int ordinal;

    POS(int ordinal) { this.ordinal = ordinal; }
}
