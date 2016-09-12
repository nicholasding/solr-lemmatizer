package com.nicholasding.search.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is a R way trie implementation.
 *
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28.
 */
public class RTrie implements Trie {

    private static class Node {
        Object value;
        Character key;
        Map<Character, Node> children = new HashMap<>();
    }

    private Node root;

    @Override
    public void put(String key, Object value) {
        root = put(root, key, value, 0);
    }

    protected Node put(Node node, String key, Object value, int level) {
        if (node == null) { node = new Node(); }
        if (level == key.length()) { node.value = value; return node; }
        Character c = key.charAt(level);

        node.children.put(c, put(node.children.get(c), key, value, level + 1));
        node.children.get(c).key = c;

        return node;
    }

    @Override
    public Object get(String key) {
        Node node = get(root, key, 0);
        if (node == null) return null;
        return node.value;
    }

    protected Node get(Node node, String key, int level) {
        if (node == null) return null;
        if (level == key.length()) return node;
        Character c = key.charAt(level);
        return get(node.children.get(c), key, level + 1);
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    @Override
    public Iterator<String> keys() {
        List<String> collector = new LinkedList<String>();

        for (Node n : root.children.values()) {
            collect(n, "", 1, collector);
        }

        return collector.iterator();
    }

    private void collect(Node node, String prefix, int level, List<String> collector) {
        if (node == null) return;
        if (node.value != null) collector.add(prefix + node.key);

        for (Node n : node.children.values()) {
            collect(n, prefix + node.key, level + 1, collector);
        }
    }

}
