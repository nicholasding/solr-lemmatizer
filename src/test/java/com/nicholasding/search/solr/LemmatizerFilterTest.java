package com.nicholasding.search.solr;

import com.nicholasding.search.lemmatization.impl.PackagedWordNetReader;
import com.nicholasding.search.lemmatization.impl.WordNetLemmatizer;
import java.io.IOException;
import java.io.StringReader;
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
        TokenStream stream = new LemmatizerFilter(in, new WordNetLemmatizer(new PackagedWordNetReader("wordnet.zip")));
        assertTokenStreamContents(stream, new String[] { "it", "better", "work" });
    }

}
