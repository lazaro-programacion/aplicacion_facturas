package com.aplicacionfacturas.aplFacturas.model;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FacturaRepository
 */

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    
}