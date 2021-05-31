package com.gustavomarques1.repositories;

import com.gustavomarques1.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
