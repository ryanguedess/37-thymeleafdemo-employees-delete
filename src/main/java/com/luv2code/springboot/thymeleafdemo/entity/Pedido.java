package com.luv2code.springboot.thymeleafdemo.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "total")
    private double total;

    @Column(name="data")
    private Date data;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "pedido", targetEntity = PedidoDetalhe.class,cascade = CascadeType.ALL)
    List<PedidoDetalhe> pedidoDetalheList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @Column(name="usuario")
    private String usuario;


    public void addDetail(PedidoDetalhe pedidoDetalhe) {
        pedidoDetalheList.add(pedidoDetalhe);
        pedidoDetalhe.setPedido(this);
    }

    public void removeDetail(PedidoDetalhe pedidoDetalhe) {
        pedidoDetalheList.remove(pedidoDetalhe);
        pedidoDetalhe.setPedido(null);
    }

    public Pedido() {
    }

    public Pedido(int id, double total, Date data, List<PedidoDetalhe> pedidoDetalheList, Cliente cliente, String usuario) {
        this.id = id;
        this.total = total;
        this.data = data;
        this.pedidoDetalheList = pedidoDetalheList;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Pedido(double total, Date data, List<PedidoDetalhe> pedidoDetalheList, String usuario) {
        this.total = total;
        this.data = data;
        this.pedidoDetalheList = pedidoDetalheList;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Pedido(int id, double total, Date data, Cliente cliente, String usuario) {
        this.id = id;
        this.total = total;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Pedido(double total, Date data, Cliente cliente, String usuario) {
        this.total = total;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Pedido( double total, Date data, String usuario) {
        this.data = data;
        this.total = total;
        this.usuario = usuario;
    }

    public Pedido(double total, String usuario){
        this.total = total;
        this.usuario= usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<PedidoDetalhe> getPedidoDetalheList() {
        return pedidoDetalheList;
    }

    public void setPedidoDetalheList(List<PedidoDetalhe> pedidoDetalheList) {
        this.pedidoDetalheList = pedidoDetalheList;
    }
}
