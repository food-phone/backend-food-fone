package com.backend.foodfone.controllers;

import com.backend.foodfone.exception.ResourceNotFoundException;
import com.backend.foodfone.models.Restaurantes;
import com.backend.foodfone.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestaurantesController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/restaurantes")
    public List<Restaurantes> getAllRestaurantes(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/restaurantes/{id}")
    public ResponseEntity<Restaurantes> getRestaurantesById(@PathVariable(value = "id")  Long restauranteId)
            throws ResourceNotFoundException {
        Restaurantes restaurantes = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurantes not found for this id :: " + restauranteId));
        return ResponseEntity.ok().body(restaurantes);
    }


    @PostMapping("/restaurantes")
    public Restaurantes createRestaurante(@Valid @RequestBody Restaurantes restaurantes) {
        return restauranteRepository.save(restaurantes);
    }

    @PutMapping("/restaurantes/{id}")
    public ResponseEntity<Restaurantes> updateRestaurante(@PathVariable(value = "id") Long restauranteId,
                                                       @Valid @RequestBody Restaurantes restaurantesDetails) throws ResourceNotFoundException {
        Restaurantes restaurantes = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurantes not found for this id :: " + restauranteId));

        restaurantes.setNome(restaurantesDetails.getNome());
        restaurantes.setFoto_url(restaurantesDetails.getFoto_url());
        final Restaurantes updatedRestaurante = restauranteRepository.save(restaurantes);
        return ResponseEntity.ok(updatedRestaurante);
    }

    @DeleteMapping("/restaurantes/{id}")
    public Map<String, Boolean> deleteRestaurante(@PathVariable(value = "id") Long restauranteId)
            throws ResourceNotFoundException {
        Restaurantes restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurantes not found for this id :: " + restauranteId));

        restauranteRepository.delete(restaurante);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
