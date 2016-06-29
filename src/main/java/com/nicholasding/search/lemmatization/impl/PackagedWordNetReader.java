package com.nicholasding.search.lemmatization.impl;

import com.nicholasding.search.lemmatization.ExceptionList;
import com.nicholasding.search.lemmatization.POS;
import com.nicholasding.search.lemmatization.WordNetReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class PackagedWordNetReader implements WordNetReader {

    private ExceptionList exceptionList = new ExceptionList();
    private Collection<String> lemmas = new LinkedList<>();

    public PackagedWordNetReader(String resource) {
        InputStream zipfile = getClass().getClassLoader().getResourceAsStream(resource);
        ZipInputStream zipIn = new ZipInputStream(zipfile);
        ZipEntry entry = null;

        try {
            while ((entry = zipIn.getNextEntry()) != null) {
                String name = entry.getName();
                if (name.endsWith(".exc")) {
                    Map<String, String> m = loadExceptionList(zipIn);
                    if ("noun.exc".equals(name)) {
                        exceptionList.addExceptionList(POS.NOUN, m);
                    } else if ("adj.exc".equals(name)) {
                        exceptionList.addExceptionList(POS.ADJECTIVE, m);
                    } else if ("adv.exc".equals(name)) {
                        exceptionList.addExceptionList(POS.ADVERB, m);
                    } else if ("verb.exc".equals(name)) {
                        exceptionList.addExceptionList(POS.VERB, m);
                    }
                } else {
                    readIndexFile(zipIn, lemmas);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected Map<String, String> loadExceptionList(ZipInputStream stream) {
        Map<String, String> m = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
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

    protected void readIndexFile(ZipInputStream stream, Collection<String> collector) {
        List<String> list = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
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
        return exceptionList;
    }

    @Override
    public Collection<String> readLemmas() {
        return lemmas;
    }

}
