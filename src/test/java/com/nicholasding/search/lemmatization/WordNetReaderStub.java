package com.nicholasding.search.lemmatization;

import java.util.*;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28
 */
public class WordNetReaderStub implements WordNetReader {

    private Map<String, String> createExceptionList(POS noun) {
        Map<String, String> m = new HashMap<String, String>();
        m.put("radii", "radius");
        return m;
    }

    @Override
    public ExceptionList readExceptionList() {
        ExceptionList list = new ExceptionList();
        list.addExceptionList(POS.NOUN, createExceptionList(POS.NOUN));
        return list;
    }

    @Override
    public Collection<String> readLemmas() {
        List<String> data = new ArrayList<String>();
        data.add("toy");
        return data;
    }

}
