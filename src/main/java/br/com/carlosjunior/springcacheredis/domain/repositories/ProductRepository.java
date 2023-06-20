package br.com.carlosjunior.springcacheredis.domain.repositories;

import br.com.carlosjunior.springcacheredis.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
