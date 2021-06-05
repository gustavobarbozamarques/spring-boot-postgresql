package br.com.gustavobarbozamarques.repositories;

import br.com.gustavobarbozamarques.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
