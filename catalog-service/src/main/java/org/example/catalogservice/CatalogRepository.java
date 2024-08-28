package org.example.catalogservice;

import org.springframework.data.repository.CrudRepository;


public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {
    CatalogEntity findByProductId(String productId);
}
