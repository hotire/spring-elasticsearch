package com.github.hotire.springelastic.core.lucene.ex;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.RAMDirectory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InMemoryLuceneIndexTest {

    @Test
    void givenTermQueryWhenFetchedDocumentThenCorrect() {
        // given
        final InMemoryLuceneIndex inMemoryLuceneIndex
                = new InMemoryLuceneIndex(new RAMDirectory(), new StandardAnalyzer());
        inMemoryLuceneIndex.indexDocument("activity", "running in track");
        inMemoryLuceneIndex.indexDocument("activity", "Cars are running on road");

        final Term term = new Term("body", "running");
        final Query query = new TermQuery(term);

        // when
        final List<Document> documents = inMemoryLuceneIndex.searchIndex(query);

        // then
        assertThat(documents.size()).isEqualTo(2);
    }

}