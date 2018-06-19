package com.reactivesearch.service.impl;

import com.reactivesearch.domain.repository.IndexedDocumentRepository;
import com.reactivesearch.rest.domain.Document;
import com.reactivesearch.rest.domain.transformers.IndexedDocumentToDocumentTransformer;
import com.reactivesearch.service.DocumentsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentsServiceImpl implements DocumentsService {

    private final IndexedDocumentRepository indexedDocumentRepository;

    public DocumentsServiceImpl(IndexedDocumentRepository documentRepository) {
        this.indexedDocumentRepository = documentRepository;
    }

    @Override
    public Document get(Integer id) {
        return IndexedDocumentToDocumentTransformer.transform(indexedDocumentRepository.get(id));
    }

    @Override
    public Document createDocument(String content) {
        final String[] tokens = content.trim().split(" +");
        final LinkedHashSet<String> contentIndex = new LinkedHashSet<>(tokens.length);
        contentIndex.addAll(Arrays.asList(tokens));
        return IndexedDocumentToDocumentTransformer.transform(indexedDocumentRepository.create(contentIndex));
    }

    @Override
    public Collection<Document> listAll() {
        return indexedDocumentRepository.all()
                .stream().map(IndexedDocumentToDocumentTransformer::transform).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, Document> search(String tokensString) {
        final Set<String> tokens = new HashSet<>(Arrays.asList(tokensString.trim().split(" +")));
        return indexedDocumentRepository.search(tokens).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> IndexedDocumentToDocumentTransformer.transform(entry.getValue())));
    }
}
