package com.example.test.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkerSearchRepository extends ElasticsearchRepository<WalkerDocument, Long> , CustomWalkerSearchRepository {


}
