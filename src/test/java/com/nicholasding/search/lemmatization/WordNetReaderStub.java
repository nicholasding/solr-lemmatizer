package com.nicholasding.search.lemmatization;

import java.util.*;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28
 */
public class WordNetReaderStub extends WordNetReader {

    public WordNetReaderStub(String dictPath) {
        super(dictPath);
    }

    @Override
    public Iterator<String> iterator() {
        List<String> data = new ArrayList<String>();
        data.add("toy");
        return data.iterator();
    }

    @Override
    public List<Map<String, String>> loadExceptionLists() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(createExceptionList(POS.NOUN));
        return list;
    }

    private Map<String, String> createExceptionList(POS noun) {
        Map<String, String> m = new HashMap<String, String>();
        m.put("radii", "radius");
        return m;
    }

}
