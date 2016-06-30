package com.nicholasding.search.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * An simple implementation of @see <a href="https://en.wikipedia.org/wiki/Ternary_search_tree">Ternary Search Tree</a>.
 *
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class TernarySearchTree implements Trie {

    private static class Node {
        Object value;
        Character key;
        Node left, middle, right;
    }

    private Node root;

    @Override
    public void put(String key, Object value) {
        root = put(root, key, value, 0);
    }

    protected Node put(Node node, String key, Object value, int level) {
        Character c = key.charAt(level);

        if (node == null) { node = new Node(); node.key = c; }

        if      (c < node.key)             node.left   = put(node.left, key, value, level);
        else if (c > node.key)             node.right  = put(node.right, key, value, level);
        else if (level < key.length() - 1) node.middle = put(node.middle, key, value, level + 1);
        else                               node.value  = value;

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

        Character c = key.charAt(level);
        if      (c < node.key)              return get(node.left, key, level);
        else if (c > node.key)              return get(node.right, key, level);
        else if (level < key.length() - 1)  return get(node.middle, key, level + 1);
        else                                return node;
    }

    @Override
    public Iterator<String> keys() {
        List<String> collector = new LinkedList<String>();
        collect(root, "", 0, collector);
        return collector.iterator();
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    private void collect(Node node, String prefix, int level, List<String> collector) {
        if (node == null) return;
        if (node.value != null) collector.add(prefix + node.key);

        collect(node.left, prefix, level, collector);
        collect(node.middle, prefix + node.key, level + 1, collector);
        collect(node.right, prefix, level, collector);
    }

}
