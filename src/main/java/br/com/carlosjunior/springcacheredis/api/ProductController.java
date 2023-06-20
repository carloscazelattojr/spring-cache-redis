package br.com.carlosjunior.springcacheredis.api;

import br.com.carlosjunior.springcacheredis.domain.entities.Product;
import br.com.carlosjunior.springcacheredis.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @Cacheable(value = "products-cache")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @CacheEvict(value = "products-cache", allEntries = true)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CachePut(value = "products-cache", key = "#product.id")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<>(service.update(id, product), HttpStatus.ACCEPTED);
    }

    @GetMapping("/clear-cache")
    @CacheEvict(value = "products-cache", allEntries = true)
    public ResponseEntity<Void> clearCache() {
        service.clearCache();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
