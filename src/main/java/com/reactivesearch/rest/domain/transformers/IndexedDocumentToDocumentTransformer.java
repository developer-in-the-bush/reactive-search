package com.reactivesearch.rest.domain.transformers;

import com.reactivesearch.domain.entity.IndexedDocument;
import com.reactivesearch.rest.domain.Document;

public class IndexedDocumentToDocumentTransformer {

    public static Document transform(IndexedDocument indexedDocument) {
        final String content = String.join(" ", indexedDocument.getIndex());
        return new Document(indexedDocument.getId(), content);
    }

}
