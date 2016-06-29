package com.nicholasding.search.util;

import java.util.Iterator;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28.
 */
public interface Trie {

    /**
     * Store the object into the Trie by providing a string key
     *
     * @param key
     * @param value
     */
    void put(String key, Object value);

    /**
     * Retrieve the object stored in the Trie
     *
     * @param key
     * @return null if key doesn't exist
     */
    Object get(String key);

    /**
     * @return iterable over all the keys
     */
    Iterator<String> keys();

    /**
     * @param key
     * @return true if key was found in the trie
     */
    boolean contains(String key);

}
