package com.reactivesearch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashSet;

@AllArgsConstructor
@Data
public class IndexedDocument {
    private final Integer id;
    private final LinkedHashSet<String> index;
}
