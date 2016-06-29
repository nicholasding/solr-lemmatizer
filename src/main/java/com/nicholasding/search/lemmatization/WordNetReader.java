package com.nicholasding.search.lemmatization;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28.
 */
public class WordNetReader implements Iterable<String> {

    private String dictPath;

    public WordNetReader(String dictPath) {
        this.dictPath = dictPath;
    }

    public List<Map<String, String>> loadExceptionLists() {
        return null;
    }

    public Iterator<String> iterator() {
        return null;
    }

}
