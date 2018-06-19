package com.reactivesearch.rest.controller;

import com.reactivesearch.rest.domain.Document;
import com.reactivesearch.service.DocumentsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documents")
public class SearchController {

    private final DocumentsService documentsService;

    public SearchController(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<Document> listAll() {
        return documentsService.listAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Document get(@PathVariable("id") Integer id) {
        return documentsService.get(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Document create(@RequestParam String content) {
        return this.documentsService.createDocument(content);
    }

    @RequestMapping(path = "/search/{tokens}", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Document> search(@PathVariable("tokens") String tokens) {
        return documentsService.search(tokens).entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }
}
