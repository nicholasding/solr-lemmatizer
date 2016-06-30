package com.nicholasding.search.solr;

import com.nicholasding.search.lemmatization.Lemmatizer;
import com.nicholasding.search.lemmatization.WordNetReader;
import com.nicholasding.search.lemmatization.impl.DirectoryWordNetReader;
import com.nicholasding.search.lemmatization.impl.PackagedWordNetReader;
import com.nicholasding.search.lemmatization.impl.WordNetLemmatizer;
import java.util.Map;

import com.nicholasding.search.util.TernarySearchTree;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class LemmatizerFilterFactory extends TokenFilterFactory {

    private static final String KEY_DICT_PATH = "dictPath";

    private Lemmatizer lemmatizer;

    protected LemmatizerFilterFactory(Map<String, String> args) {
        super(args);

        WordNetReader reader;

        if (args.containsKey(KEY_DICT_PATH)) {
            String path = args.get(KEY_DICT_PATH);
            reader = new DirectoryWordNetReader(path);
        } else {
            reader = new PackagedWordNetReader("wordnet.zip");
        }

        lemmatizer = new WordNetLemmatizer(reader, new TernarySearchTree());
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new LemmatizerFilter(tokenStream, lemmatizer);
    }

}
