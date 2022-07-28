package com.github.hotire.springelastic.core.lucene.ex;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryLuceneIndex {
    private final Directory memoryIndex;
    private final Analyzer analyzer;

    public InMemoryLuceneIndex(Directory memoryIndex, Analyzer analyzer) {
        super();
        this.memoryIndex = memoryIndex;
        this.analyzer = analyzer;
    }

    public void indexDocument(final String title, final String body) {
        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        try (final IndexWriter writer = new IndexWriter(memoryIndex, indexWriterConfig)) {
            final Document document = new Document();

            document.add(new TextField("title", title, Field.Store.YES));
            document.add(new TextField("body", body, Field.Store.YES));
            document.add(new SortedDocValuesField("title", new BytesRef(title)));

            System.out.println("add, document : " + document);
            writer.addDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Document> searchIndex(final String inField, final String queryString) {
        try (final IndexReader indexReader = DirectoryReader.open(memoryIndex)) {
            final Query query = new QueryParser(inField, analyzer).parse(queryString);
            final IndexSearcher searcher = new IndexSearcher(indexReader);
            final TopDocs topDocs = searcher.search(query, 10);
            final List<Document> documents = new ArrayList<>();

            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                documents.add(searcher.doc(scoreDoc.doc));
            }

            return documents;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public void deleteDocument(final Term term) {
        final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        try (final IndexWriter writer = new IndexWriter(memoryIndex, indexWriterConfig)) {
            writer.deleteDocuments(term);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Document> searchIndex(final Query query) {
        try (final IndexReader indexReader = DirectoryReader.open(memoryIndex)) {
            final IndexSearcher searcher = new IndexSearcher(indexReader);
            final TopDocs topDocs = searcher.search(query, 10);
            final List<Document> documents = new ArrayList<>();

            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                documents.add(searcher.doc(scoreDoc.doc));
            }

            return documents;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public List<Document> searchIndex(final Query query, final Sort sort) {
        try (final IndexReader indexReader = DirectoryReader.open(memoryIndex)) {
            final IndexSearcher searcher = new IndexSearcher(indexReader);
            final TopDocs topDocs = searcher.search(query, 10, sort);
            final List<Document> documents = new ArrayList<>();

            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                documents.add(searcher.doc(scoreDoc.doc));
            }

            return documents;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
