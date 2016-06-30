package com.nicholasding.search.lemmatization.impl;

import com.nicholasding.search.util.RTrie;
import com.nicholasding.search.util.TernarySearchTree;
import com.nicholasding.search.util.Trie;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class BenchmarkTest {

    @Test
    public void benchmarkRTrie() {
        long start = System.currentTimeMillis();
        WordNetLemmatizer lemmatizer = new WordNetLemmatizer(new PackagedWordNetReader("wordnet.zip"), new RTrie());
        System.out.println("RTrie initialize: " + (System.currentTimeMillis() - start) + " ms");

        Trie trie = lemmatizer.getTrie();
        benchmark(trie);
    }

    @Test
    public void benchmarkTST() {
        long start = System.currentTimeMillis();
        WordNetLemmatizer lemmatizer = new WordNetLemmatizer(new PackagedWordNetReader("wordnet.zip"), new TernarySearchTree());
        System.out.println("TST initialize: " + (System.currentTimeMillis() - start) + " ms");

        Trie trie = lemmatizer.getTrie();
        benchmark(trie);
    }

    private void benchmark(Trie trie) {
        long start = System.currentTimeMillis();

        long counter = 0;
        Iterator<String> keys = trie.keys();
        while (keys.hasNext()) {
            trie.get(keys.next());
            counter++;
        }

        long end = System.currentTimeMillis();

        float avg = (float) (end - start) / counter * 1000000;
        System.out.println("Total access time: " + (end - start) + " ms, " + counter + " lookups, " + avg + " ns/lookup");
    }

}
