package com.github.hotire.springelastic.core.lucene.index;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;

import java.io.IOException;

/**
 * @see IndexWriter
 */
public class IndexWriterCore {

    /**
     * @see IndexWriter#addDocument(Iterable)
     */
    public long addDocument(Iterable<? extends IndexableField> doc) throws IOException {
        return 0L;
    }

    /**
     * @see IndexWriter#commit()
     */
    public final long commit() throws IOException {
        return 0L;
    }

    /**
     * @see IndexWriter#flush()
     */
    public final void flush() throws IOException {
    }
}
