package br.com.carlosjunior.springcacheredis.domain.services;

import br.com.carlosjunior.springcacheredis.domain.entities.Product;
import br.com.carlosjunior.springcacheredis.domain.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;
    public List<Product> findAll(){
        log.info("Listar todos os produtos");
        return repository.findAll();
    }

    public Optional<Product> findById(Long id){
        log.info("Buscando produto por id");
        return repository.findById(id) ;
    }

    public void clearCache(){
        log.info("Limpando Cache");
    }

    

}
