package com.example.test.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomWalkerSearchRepository {
  Page <WalkerDocument> searchByName(final WalkerInfoRequest walkerInfoRequest , final
      Pageable pageable);
}
