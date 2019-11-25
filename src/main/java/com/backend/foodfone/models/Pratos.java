package com.backend.foodfone.models;

import javax.persistence.*;

@Entity
@Table(name = "pratos")
public class Pratos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prato")
    private Long id;

    private Long restauranteID;

    private String nomePrato;

    private String fotoUrl;

    @Column(columnDefinition="text", length=10485760)
    private String fotosArray ;

    private String tempoPreparo;

    @Column(columnDefinition="text", length=10485760)
    private String ingredientes;

    public Pratos(){}

    public Pratos(Long restauranteID, String nomePrato, String fotoUrl, String fotosArray, String tempoPreparo, String ingredientes) {
        this.restauranteID = restauranteID;
        this.nomePrato = nomePrato;
        this.fotoUrl = fotoUrl;
        this.fotosArray = fotosArray;
        this.tempoPreparo = tempoPreparo;
        this.ingredientes = ingredientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestauranteID() {
        return restauranteID;
    }

    public void setRestauranteID(Long restauranteID) {
        this.restauranteID = restauranteID;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getFotosArray() {
        return fotosArray;
    }

    public void setFotosArray(String fotosArray) {
        this.fotosArray = fotosArray;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
}
