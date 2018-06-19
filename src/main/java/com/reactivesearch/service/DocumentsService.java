package com.reactivesearch.service;

import com.reactivesearch.rest.domain.Document;

import java.util.Collection;
import java.util.Map;

public interface DocumentsService {

    Document get(Integer id);

    Document createDocument(String content);

    Collection<Document> listAll();

    Map<Integer, Document> search(String tokensString);
}
