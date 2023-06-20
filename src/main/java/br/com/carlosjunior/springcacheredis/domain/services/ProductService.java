package br.com.carlosjunior.springcacheredis.domain.services;

import br.com.carlosjunior.springcacheredis.domain.entities.Product;
import br.com.carlosjunior.springcacheredis.domain.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/*
* Tenho duas chaves para Cache: ProductsAll e o Product.
*
* */

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Cacheable(value = "ProductsAll")
    public List<Product> findAll(){
        log.info("Listar todos os produtos");
        return repository.findAll();
    }

    @Cacheable(value = "Product", key = "#id")
    public Optional<Product> findById(Long id){
        log.info("Buscando produto por id");
        return repository.findById(id) ;
    }

    @Caching(evict = { @CacheEvict(value = "Product", key = "#id"),
            @CacheEvict(value = "ProductsAll", allEntries = true) })
    public void clearCache(){
        log.info("Limpando Cache");
    }

    @CacheEvict(value = "ProductsAll", allEntries = true)
    public Product create(Product product){
        log.info("Inserindo novo produto");
        return repository.save(product);
    }

    @CachePut(value = "Product", key = "#id")
    public Product update(Long id, Product product) {
        log.info("Atualizando produto");
        Product productSave = repository.findById(id).get();
        productSave.setName(product.getName());
        productSave.setDescription(product.getDescription());
        return repository.save(productSave);

    }
}
