package com.nicholasding.search.solr;

import com.nicholasding.search.lemmatization.Lemmatizer;
import com.nicholasding.search.lemmatization.POS;
import java.io.IOException;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class LemmatizerFilter extends TokenFilter {

    private final CharTermAttribute termAtt = (CharTermAttribute)this.addAttribute(CharTermAttribute.class);
    private final Lemmatizer lemmatizer;

    public LemmatizerFilter(TokenStream tokenStream, Lemmatizer lemmatizer) {
        super(tokenStream);
        this.lemmatizer = lemmatizer;
    }

    @Override
    public final boolean incrementToken() throws IOException {
        if (!this.input.incrementToken()) {
            return false;
        } else {
            char[] termBuffer = this.termAtt.buffer();
            String lemma = lemmatizer.stem(new String(termBuffer, 0, this.termAtt.length()), null);
            this.termAtt.copyBuffer(lemma.toCharArray(), 0, lemma.length());
            return true;
        }
    }

}
