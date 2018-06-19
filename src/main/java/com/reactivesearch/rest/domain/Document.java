package com.reactivesearch.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class Document {
    private final Integer id;
    private final String tokens;
}
