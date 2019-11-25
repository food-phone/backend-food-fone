package com.backend.foodfone.repositories;

import com.backend.foodfone.models.Pratos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface PratosRepository extends JpaRepository<Pratos, Long> {
}
