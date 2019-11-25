package com.backend.foodfone.controllers;

import com.backend.foodfone.exception.ResourceNotFoundException;
import com.backend.foodfone.models.Compra;
import com.backend.foodfone.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;


    @GetMapping("/compras")
    public List<Compra> getAllCompra(){
        return compraRepository.findAll();
    }

    @GetMapping("/compras/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable(value = "id")  Long comprasId)
            throws ResourceNotFoundException {
        Compra pratos = compraRepository.findById(comprasId)
                .orElseThrow(() -> new ResourceNotFoundException("Compras not found for this id :: " + comprasId));
        return ResponseEntity.ok().body(pratos);
    }


    @PostMapping("/compras")
    public Compra createCompra(@Valid @RequestBody Compra compra) {
        return compraRepository.save(compra);
    }

    @PutMapping("/compras/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable(value = "id") Long comprasId,
                                              @Valid @RequestBody Compra compraDetails) throws ResourceNotFoundException {
        Compra compra = compraRepository.findById(comprasId)
                .orElseThrow(() -> new ResourceNotFoundException("Compras not found for this id :: " + comprasId));

        compra.setPreco(compraDetails.getPreco());
        compra.setUsuario(compraDetails.getUsuario());
        compra.setPrato(compraDetails.getPrato());
        compra.setEndereco(compraDetails.getEndereco());
        compra.setCep(compraDetails.getCep());

        final Compra updatedCompra = compraRepository.save(compra);
        return ResponseEntity.ok(updatedCompra);
    }

    @DeleteMapping("/compras/{id}")
    public Map<String, Boolean> deleteCompra(@PathVariable(value = "id") Long comprasId)
            throws ResourceNotFoundException {
        Compra compra = compraRepository.findById(comprasId)
                .orElseThrow(() -> new ResourceNotFoundException("Compras not found for this id :: " + comprasId));

        compraRepository.delete(compra);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
