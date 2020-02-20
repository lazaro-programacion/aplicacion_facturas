package com.aplicacionfacturas.aplFacturas.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * FacturaRepository
 */

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    Optional<Producto> findById(Long id);
}