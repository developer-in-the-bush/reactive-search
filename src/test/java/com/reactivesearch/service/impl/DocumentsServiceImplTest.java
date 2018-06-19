package com.reactivesearch.service.impl;

import com.reactivesearch.domain.entity.IndexedDocument;
import com.reactivesearch.domain.repository.impl.IndexedDocumentRepositoryImpl;
import com.reactivesearch.rest.domain.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DocumentsServiceImplTest {

    private static final String TOKENIZED_TEXT_STUB = "Hello I'am content ";
    private static final String TOKENIZED_TEXT_STUB_2 = "What's up?";
    private static final String TOKENIZED_TEXT_STUB_3 = " I'am the content !";
    private static final String TOKENIZED_TEXT_STUB_4 = "Another string to pass by";
    private IndexedDocumentRepositoryImpl indexedDocumentRepository;


    @Before
    public void preparation() {
        indexedDocumentRepository = new IndexedDocumentRepositoryImpl();
    }

    @Test
    public void assertCorrectIterationOrder() {
        DocumentsServiceImpl serviceUnderTest = new DocumentsServiceImpl(indexedDocumentRepository);
        final Document createdDocument = serviceUnderTest.createDocument(TOKENIZED_TEXT_STUB);
        final IndexedDocument indexedDocument = indexedDocumentRepository.get(createdDocument.getId());
        final LinkedHashSet<String> index = indexedDocument.getIndex();
        Iterator<String> iterator = index.iterator();
        assertThat(iterator.next()).isEqualTo("Hello");
        assertThat(iterator.next()).isEqualTo("I'am");
        assertThat(iterator.next()).isEqualTo("content");
    }

    @Test
    public void testSimpleSearch() {
        DocumentsServiceImpl serviceUnderTest = new DocumentsServiceImpl(indexedDocumentRepository);
        serviceUnderTest.createDocument(TOKENIZED_TEXT_STUB);
        serviceUnderTest.createDocument(TOKENIZED_TEXT_STUB_2);
        serviceUnderTest.createDocument(TOKENIZED_TEXT_STUB_3);
        serviceUnderTest.createDocument(TOKENIZED_TEXT_STUB_4);

        final Map<Integer, Document> foundDocuments = serviceUnderTest.search("I'am content");
        assertTrue(foundDocuments.containsKey(0) && foundDocuments.containsKey(2));
    }
}
