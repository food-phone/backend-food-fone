package com.backend.foodfone.controllers;

import com.backend.foodfone.exception.ResourceNotFoundException;
import com.backend.foodfone.models.Ingredientes;
import com.backend.foodfone.repositories.IngredientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IngredientesController {
    @Autowired
    private IngredientesRepository ingredientesRepository;

    @GetMapping("/ingredientes")
    public List<Ingredientes> getAllIngredientes(){
        return ingredientesRepository.findAll();
    }

    @GetMapping("/ingredientes/{id}")
    public ResponseEntity<Ingredientes> getIngredientesById(@PathVariable(value = "id")  Long ingredienteId)
            throws ResourceNotFoundException {
        Ingredientes ingredientes = ingredientesRepository.findById(ingredienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredientes not found for this id :: " + ingredienteId));
        return ResponseEntity.ok().body(ingredientes);
    }


    @PostMapping("/ingredientes")
    public Ingredientes createIngrediente(@Valid @RequestBody Ingredientes ingredientes) {
        return ingredientesRepository.save(ingredientes);
    }

    @PutMapping("/ingredientes/{id}")
    public ResponseEntity<Ingredientes> updateIngrediente(@PathVariable(value = "id") Long ingredienteId,
                                                   @Valid @RequestBody Ingredientes ingredientesDetails) throws ResourceNotFoundException {
        Ingredientes ingredientes = ingredientesRepository.findById(ingredienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredientes not found for this id :: " + ingredienteId));

        ingredientes.setNome(ingredientesDetails.getNome());
        ingredientes.setFoto_url(ingredientesDetails.getFoto_url());
        final Ingredientes updatedIngredientes = ingredientesRepository.save(ingredientes);
        return ResponseEntity.ok(updatedIngredientes);
    }

    @DeleteMapping("/ingredientes/{id}")
    public Map<String, Boolean> deleteIngrediente(@PathVariable(value = "id") Long ingredienteId)
            throws ResourceNotFoundException {
        Ingredientes ingredientes = ingredientesRepository.findById(ingredienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredientes not found for this id :: " + ingredienteId));

        ingredientesRepository.delete(ingredientes);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
