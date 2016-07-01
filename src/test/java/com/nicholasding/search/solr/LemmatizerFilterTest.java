package com.nicholasding.search.solr;

import com.nicholasding.search.lemmatization.impl.PackagedWordNetReader;
import com.nicholasding.search.lemmatization.impl.WordNetLemmatizer;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.nicholasding.search.util.RTrie;
import com.nicholasding.search.util.TernarySearchTree;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class LemmatizerFilterTest extends BaseTokenStreamTestCase {

    @Test
    public void testWithSamplePhrase() throws IOException {
        StringReader reader = new StringReader("it better works");
        final MockTokenizer in = new MockTokenizer(MockTokenizer.WHITESPACE, false);
        in.setReader(reader);
        TokenStream stream = new LemmatizerFilter(in, new WordNetLemmatizer(new PackagedWordNetReader("wordnet.zip"), new RTrie()));
        assertTokenStreamContents(stream, new String[] { "it", "good", "work" });
    }

    @Test
    public void testUsingPackagedWordNetReaderFromFilterFactory() throws IOException {
        Map<String, String> args = new HashMap<>();
        LemmatizerFilterFactory factory = new LemmatizerFilterFactory(args);

        StringReader reader = new StringReader("it better works");
        final MockTokenizer in = new MockTokenizer(MockTokenizer.WHITESPACE, false);
        in.setReader(reader);
        TokenStream stream = factory.create(in);
        assertTokenStreamContents(stream, new String[] { "it", "good", "work" });
    }

    @Test
    public void testUsingDirectoryWordNetReaderWithDummyPathShouldFailSilently() throws IOException {
        Map<String, String> args = new HashMap<>();
        args.put("dictPath", "/tmp");
        LemmatizerFilterFactory factory = new LemmatizerFilterFactory(args);

        StringReader reader = new StringReader("it better works");
        final MockTokenizer in = new MockTokenizer(MockTokenizer.WHITESPACE, false);
        in.setReader(reader);
        TokenStream stream = factory.create(in);
        assertTokenStreamContents(stream, new String[] { "it", "better", "works" });
    }

}
