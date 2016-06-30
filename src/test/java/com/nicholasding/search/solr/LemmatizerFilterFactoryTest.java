package com.nicholasding.search.solr;

import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class LemmatizerFilterFactoryTest extends BaseTokenStreamTestCase {

    @Test
    public void testUsingPackagedWordNetReader() throws IOException {
        Map<String, String> args = new HashMap<>();
        LemmatizerFilterFactory factory = new LemmatizerFilterFactory(args);
        StringReader reader = new StringReader("it better works");
        final MockTokenizer in = new MockTokenizer(MockTokenizer.WHITESPACE, false);
        in.setReader(reader);
        TokenStream stream = factory.create(in);
        assertTokenStreamContents(stream, new String[] { "it", "better", "work" });
    }

}
