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

        final Term bodyTerm = new Term("body", "running");
        final Term titleTerm = new Term("title", "activity");
        final Query bodyQuery = new TermQuery(bodyTerm);
        final Query titleQuery = new TermQuery(titleTerm);

        // when
        final List<Document> bodyDocuments = inMemoryLuceneIndex.searchIndex(bodyQuery);
        final List<Document> titleDocuments = inMemoryLuceneIndex.searchIndex(titleQuery);

        // then
        assertThat(bodyDocuments.size()).isEqualTo(2);
        assertThat(titleDocuments.size()).isEqualTo(2);
    }

}