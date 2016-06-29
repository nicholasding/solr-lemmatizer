package com.nicholasding.search.lemmatization;

import java.util.Collection;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public interface WordNetReader {

    /**
     * @return exception list for four categories
     */
    ExceptionList readExceptionList();

    /**
     * @return all the lemmas
     */
    Collection<String> readLemmas();

}
