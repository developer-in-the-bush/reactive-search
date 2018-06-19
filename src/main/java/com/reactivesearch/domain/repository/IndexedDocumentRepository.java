package com.reactivesearch.domain.repository;

import com.reactivesearch.domain.entity.IndexedDocument;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public interface IndexedDocumentRepository {

    IndexedDocument create(LinkedHashSet<String> index);

    IndexedDocument get(Integer id);

    Collection<IndexedDocument> all();

    /**
     * @return Set of documents which has all tokens in body.
     */
    Map<Integer, IndexedDocument> search(Set<String> tokens);
}
