package br.com.carlosjunior.springcacheredis.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.NotFound;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

@Entity
@Table(name = "product")
@Data
public class Product implements RedisSerializer<Product> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Override
    public byte[] serialize(Product product) throws SerializationException {
        return SerializationUtils.serialize(product);
    }

    @Override
    public Product deserialize(byte[] bytes) throws SerializationException {
        return (Product) SerializationUtils.deserialize(bytes);
    }
}
