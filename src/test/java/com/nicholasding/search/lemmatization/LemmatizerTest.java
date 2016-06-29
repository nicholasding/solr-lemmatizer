package com.nicholasding.search.lemmatization;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-28
 */
public class LemmatizerTest {

    @Test
    public void testStemShouldGetResultFromExceptionList() {
        Lemmatizer lemmatizer = new WordNetLemmatizer(new WordNetReaderStub("/path/to/dict"));
        Assert.assertEquals("radius", lemmatizer.stem("radii", POS.NOUN));
    }

    @Test
    public void testStemShouldGetResultFromTrie() {
        Lemmatizer lemmatizer = new WordNetLemmatizer(new WordNetReaderStub("/path/to/dict"));
        Assert.assertEquals("toy", lemmatizer.stem("toys", POS.NOUN));
    }

    @Test
    public void testStemShouldReturnOriginalForm() {
        Lemmatizer lemmatizer = new WordNetLemmatizer(new WordNetReaderStub("/path/to/dict"));
        Assert.assertEquals("toy", lemmatizer.stem("toy", POS.NOUN));
    }

}
