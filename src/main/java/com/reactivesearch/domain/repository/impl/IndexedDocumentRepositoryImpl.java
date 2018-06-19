package com.reactivesearch.domain.repository.impl;

import com.reactivesearch.domain.entity.IndexedDocument;
import com.reactivesearch.domain.repository.IndexedDocumentRepository;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class IndexedDocumentRepositoryImpl implements IndexedDocumentRepository {

    private final static int INITIAL_STORAGE_CAPACITY = 10;

    private final ConcurrentHashMap<Integer, IndexedDocument> internalStorage;
    private final AtomicInteger incrementSequence;

    public IndexedDocumentRepositoryImpl() {
        this.internalStorage = new ConcurrentHashMap<>(INITIAL_STORAGE_CAPACITY);
        this.incrementSequence = new AtomicInteger();
    }

    @Override
    public IndexedDocument create(final LinkedHashSet<String> indexedContent) {
        final IndexedDocument indexedDocument = new IndexedDocument(incrementSequence.getAndIncrement(), indexedContent);
        internalStorage.put(indexedDocument.getId(), indexedDocument);
        return indexedDocument;
    }

    @Override
    public IndexedDocument get(Integer id) {
        return internalStorage.get(id);
    }

    @Override
    public Collection<IndexedDocument> all() {
        return internalStorage.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, IndexedDocument> search(Set<String> tokens) {
        return internalStorage.entrySet().stream().filter((entity) -> entity.getValue().getIndex().containsAll(tokens))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
