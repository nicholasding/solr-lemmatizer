package com.nicholasding.search.solr;

import com.nicholasding.search.lemmatization.Lemmatizer;
import com.nicholasding.search.lemmatization.impl.PackagedWordNetReader;
import com.nicholasding.search.lemmatization.impl.WordNetLemmatizer;
import java.util.Map;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class LemmatizerFilterFactory extends TokenFilterFactory {

    private Lemmatizer lemmatizer;

    protected LemmatizerFilterFactory(Map<String, String> args) {
        super(args);
        lemmatizer = new WordNetLemmatizer(new PackagedWordNetReader("wordnet.zip"));
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new LemmatizerFilter(tokenStream, lemmatizer);
    }

}
