# solr-lemmatizer

This is a Solr analysis plugin that uses [WordNet](https://wordnet.princeton.edu/wordnet/documentation/) database to do [lemmatization](http://nlp.stanford.edu/IR-book/html/htmledition/stemming-and-lemmatization-1.html) . 

```
<fieldType name="text" class="solr.TextField">
  <analyzer>
    <tokenizer class="solr.StandardTokenizerFactory"/>
    <filter class="solr.StandardFilterFactory"/>
    <filter class="solr.LowerCaseFilterFactory"/>
    <filter class="solr.StopFilterFactory"/>
    <filter class="com.nicholasding.search.solr.LemmatizerFilterFactory"/>
  </analyzer>
</fieldType>
```