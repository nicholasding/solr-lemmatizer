package com.nicholasding.search.lemmatization.impl;

import com.nicholasding.search.lemmatization.ExceptionList;
import com.nicholasding.search.lemmatization.Lemmatizer;
import com.nicholasding.search.lemmatization.POS;
import com.nicholasding.search.lemmatization.WordNetReader;
import com.nicholasding.search.util.RTrie;
import com.nicholasding.search.util.TernarySearchTree;
import com.nicholasding.search.util.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28
 */
public class WordNetLemmatizer implements Lemmatizer {

    private Trie trie;
    private ExceptionList exceptionList;
    private DetachmentRules rules;

    /**
     * Default constructor will load the required resources and construct the trie.
     *
     * @param reader
     */
    public WordNetLemmatizer(WordNetReader reader, Trie trie) {
        this.exceptionList = reader.readExceptionList();
        this.trie = trie;
        this.rules = new DetachmentRules();

        buildTrie(reader, trie);
    }

    private void buildTrie(WordNetReader reader, Trie trie) {
        for (String word : reader.readLemmas()) {
            trie.put(word, Boolean.TRUE);
        }
    }

    public String stem(String word, POS pos) {
        if (word == null || word.length() == 0) return null;

        String exception = checkExceptionList(word, pos);
        if (exception != null) return exception;

        String[] candidates = rules.apply(word, pos);
        for (String candidate : candidates) {
            if (trie.contains(candidate)) return candidate;
        }

        return word;
    }

    protected String checkExceptionList(String word, POS pos) {
        return exceptionList.lookupException(word, pos);
    }

    protected Trie getTrie() {
        return trie;
    }

}
