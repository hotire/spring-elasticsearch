package com.github.hotire.springelastic.core.lucene.analysis;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.jupiter.api.Test;

class AnalyzerCoreTest {
    private static final String SAMPLE_TEXT = "This is baeldung.com Lucene Analyzers test";
    private static final String FIELD_NAME = "sampleName";

    @Test
    void whenUseStandardAnalyzer_thenAnalyzed() throws IOException {
        // when
        final List<String> result = analyze(SAMPLE_TEXT, new StandardAnalyzer());

        // then
        assertThat(result).contains("baeldung.com", "lucene", "analyzers", "test");
    }

    private List<String> analyze(final String text, final Analyzer analyzer) throws IOException {
        final List<String> result = new ArrayList<>();
        final TokenStream tokenStream = analyzer.tokenStream(FIELD_NAME, text);
        final CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            result.add(attr.toString());
        }
        return result;
    }
}