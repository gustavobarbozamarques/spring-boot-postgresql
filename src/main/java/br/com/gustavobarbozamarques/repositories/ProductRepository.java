package br.com.gustavobarbozamarques.repositories;

import br.com.gustavobarbozamarques.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer categoryId);
}
