package com.nicholasding.search.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28.
 */
public class TrieTest {

    @Test
    public void testRTriePut() {
        Trie trie = new RTrie();
        trie.put("ab", "ab");

        Assert.assertEquals("ab", trie.get("ab"));
    }

    @Test
    public void testIterateAllKeys() {
        Trie trie = new RTrie();
        trie.put("ab", "ab");
        trie.put("bc", "bc");
        trie.put("cd", "cd");

        Iterator<String> keys = trie.keys();
        Assert.assertEquals("ab", keys.next());
        Assert.assertEquals("bc", keys.next());
        Assert.assertEquals("cd", keys.next());
    }

}
