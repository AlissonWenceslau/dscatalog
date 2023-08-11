package com.devsuperior.dscatalog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT obj FROM Product obj INNER JOIN obj.categories cat WHERE "
			+ "(:categoryId IS NULL OR :categoryId IN cat) AND "
			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%')))")
	Page<Product> find(Category categoryId, String name, Pageable pageable);

}
