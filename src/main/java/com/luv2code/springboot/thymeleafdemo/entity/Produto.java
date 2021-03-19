package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;

@Entity
@Table(name="produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="nome")
    private String nome;

    @Column(name="imagem")
    private String imagem;

    public Produto() {
    }

    public Produto(int id, String produto, String imagem) {
        this.id = id;
        this.nome = produto;
        this.imagem = imagem;
    }

    public Produto(String produto, String imagem) {
        this.nome = produto;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Transient
    public String getImagePath(){
        if(imagem == null) return null;

        return "/fotos-produtos/" + id + "/" + imagem;
    }
}
