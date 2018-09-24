package com.microfocus.oo.poc.websocketstreaming.repository;

import com.microfocus.oo.poc.websocketstreaming.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String categoryName);

}
