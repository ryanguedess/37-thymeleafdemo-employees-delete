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

    @Column(name="preco")
    private Double preco;

    public Produto() {
    }

    public Produto(int id, String nome, String imagem, Double preco) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.preco = preco;
    }

    public Produto(String nome, String imagem, Double preco) {
        this.nome = nome;
        this.imagem = imagem;
        this.preco = preco;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
