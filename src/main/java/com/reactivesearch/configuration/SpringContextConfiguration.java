package com.reactivesearch.configuration;

import com.reactivesearch.domain.repository.IndexedDocumentRepository;
import com.reactivesearch.domain.repository.impl.IndexedDocumentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContextConfiguration {

    @Bean
    public IndexedDocumentRepository indexedDocumentRepository() {
        return new IndexedDocumentRepositoryImpl();
    }
}
