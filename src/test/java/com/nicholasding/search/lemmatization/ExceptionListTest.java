package com.nicholasding.search.lemmatization;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nicholas Ding (nicholasdsj@gmail.com) on 2016-06-29
 */
public class ExceptionListTest {

    @Test
    public void testInsertAndLookup() {
        ExceptionList excList = new ExceptionList();
        Map<String, String> m = new HashMap<>();
        m.put("a", "b");
        excList.addExceptionList(POS.NOUN, m);

        Assert.assertEquals("b", excList.lookupException("a"));
        Assert.assertEquals("b", excList.lookupException("a", POS.NOUN));
        Assert.assertEquals(null, excList.lookupException("a", POS.VERB));
    }

    @Test
    public void testMergeInput() {
        ExceptionList excList = new ExceptionList();
        Map<String, String> m = new HashMap<>();
        m.put("a", "b");

        excList.addExceptionList(POS.NOUN, m);

        m.put("c", "d");
        excList.addExceptionList(POS.NOUN, m);

        Assert.assertEquals("b", excList.lookupException("a"));
        Assert.assertEquals("d", excList.lookupException("c"));
    }

}
