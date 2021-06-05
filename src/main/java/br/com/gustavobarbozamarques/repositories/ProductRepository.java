package br.com.gustavobarbozamarques.repositories;

import br.com.gustavobarbozamarques.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
