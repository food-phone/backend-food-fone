package com.backend.foodfone.repositories;

import com.backend.foodfone.models.Restaurantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurantes, Long> {
}
