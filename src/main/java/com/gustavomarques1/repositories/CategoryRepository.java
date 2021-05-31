package com.gustavomarques1.repositories;

import com.gustavomarques1.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
