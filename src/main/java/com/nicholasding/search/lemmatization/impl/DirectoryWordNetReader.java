package com.nicholasding.search.lemmatization.impl;

import com.nicholasding.search.lemmatization.ExceptionList;
import com.nicholasding.search.lemmatization.POS;
import com.nicholasding.search.lemmatization.WordNetReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28.
 */
public class DirectoryWordNetReader implements WordNetReader {

    private String dictPath;

    public DirectoryWordNetReader(String dictPath) {
        this.dictPath = dictPath;
    }

    protected Map<String, String> loadExceptionList(File file) {
        Map<String, String> m = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split("\\s+");
                m.put(cols[0], cols[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return m;
    }

    protected void readIndexFile(File file, Collection<String> collector) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(" ")) {
                    String[] cols = line.split("\\s+");
                    collector.add(cols[0]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ExceptionList readExceptionList() {
        ExceptionList list = new ExceptionList();

        File dir = new File(dictPath);
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".exc");
            }
        });

        for (File file : files) {
            Map<String, String> m = loadExceptionList(file);
            if ("noun.exc".equals(file.getName())) {
                list.addExceptionList(POS.NOUN, m);
            } else if ("adj.exc".equals(file.getName())) {
                list.addExceptionList(POS.ADJECTIVE, m);
            } else if ("adv.exc".equals(file.getName())) {
                list.addExceptionList(POS.ADVERB, m);
            } else if ("verb.exc".equals(file.getName())) {
                list.addExceptionList(POS.VERB, m);
            }
        }

        return list;
    }

    @Override
    public Collection<String> readLemmas() {
        Collection<String> lemmas = new LinkedList<>();

        File dir = new File(dictPath);
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().startsWith("index.") && !pathname.getName().endsWith(".sense");
            }
        });

        for (File f : files) {
            readIndexFile(f, lemmas);
        }

        return lemmas;
    }

}
