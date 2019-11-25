package com.backend.foodfone.controllers;

import com.backend.foodfone.exception.ResourceNotFoundException;
import com.backend.foodfone.models.Pratos;
import com.backend.foodfone.repositories.PratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PratosController {
    @Autowired
    private PratosRepository pratosRepository;


    @GetMapping("/pratos")
    public List<Pratos> getAllPratos(){
        return pratosRepository.findAll();
    }

    @GetMapping("/pratos/{id}")
    public ResponseEntity<Pratos> getPratoById(@PathVariable(value = "id")  Long pratosId)
            throws ResourceNotFoundException {
        Pratos pratos = pratosRepository.findById(pratosId)
                .orElseThrow(() -> new ResourceNotFoundException("Pratos not found for this id :: " + pratosId));
        return ResponseEntity.ok().body(pratos);
    }


    @PostMapping("/pratos")
    public Pratos createPrato(@Valid @RequestBody Pratos pratos) {
        return pratosRepository.save(pratos);
    }

    @PutMapping("/pratos/{id}")
    public ResponseEntity<Pratos> updatePrato(@PathVariable(value = "id") Long pratosId,
                                                       @Valid @RequestBody Pratos pratosDetails) throws ResourceNotFoundException {
        Pratos pratos = pratosRepository.findById(pratosId)
                .orElseThrow(() -> new ResourceNotFoundException("pratos not found for this id :: " + pratosId));

        pratos.setNomePrato(pratosDetails.getNomePrato());
        pratos.setFotoUrl(pratosDetails.getFotoUrl());
        pratos.setRestauranteID(pratosDetails.getRestauranteID());
        pratos.setFotosArray(pratosDetails.getFotosArray());
        pratos.setTempoPreparo(pratosDetails.getTempoPreparo());
        pratos.setIngredientes(pratosDetails.getIngredientes());

        final Pratos updatedPratos = pratosRepository.save(pratos);
        return ResponseEntity.ok(updatedPratos  );
    }

    @DeleteMapping("/pratos/{id}")
    public Map<String, Boolean> deletePrato(@PathVariable(value = "id") Long pratosId)
            throws ResourceNotFoundException {
        Pratos pratos = pratosRepository.findById(pratosId)
                .orElseThrow(() -> new ResourceNotFoundException("pratos not found for this id :: " + pratosId));

        pratosRepository.delete(pratos);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
