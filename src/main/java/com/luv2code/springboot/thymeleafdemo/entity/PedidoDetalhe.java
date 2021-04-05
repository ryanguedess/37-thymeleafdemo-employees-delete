package com.luv2code.springboot.thymeleafdemo.entity;

import javax.persistence.*;

@Entity
@Table(name="pedidos_detalhe")
public class PedidoDetalhe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="total")
    private Double total;

    @Column(name="preco")
    private Double preco;

    @Column(name = "quantidade")
    private  int quantidade;

    @ManyToOne
    @JoinColumn(name="id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name="id_produto")
    private Produto produto;



    public PedidoDetalhe() {
    }

    public PedidoDetalhe(int id, Double total, Double preco, int quantidade, Pedido pedido, Produto produto) {
        this.id = id;
        this.total = total;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
    }

    public PedidoDetalhe(Double total, Double preco, int quantidade, Pedido pedido, Produto produto) {
        this.total = total;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
    }
    public PedidoDetalhe(Double total, Double preco, int quantidade, Produto produto) {
        this.total = total;
        this.preco = preco;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
