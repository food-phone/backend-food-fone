package com.backend.foodfone.models;

import javax.persistence.*;

@Entity
@Table(name = "restaurantes")
public class Restaurantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_restaurante")
    private Long id;
    private String nome;
    private String foto_url;

    // Contrutores
    public Restaurantes(){

    }

    public Restaurantes(String nome, String foto_url) {
        this.nome = nome;
        this.foto_url = foto_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }
}
