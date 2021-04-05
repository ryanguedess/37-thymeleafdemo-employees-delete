package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.FIELD;
import com.luv2code.springboot.thymeleafdemo.dao.PedidoDetalheRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PedidoRepository;
import com.luv2code.springboot.thymeleafdemo.dao.ProdutoRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.entity.Pedido;
import com.luv2code.springboot.thymeleafdemo.entity.PedidoDetalhe;
import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.paging.Paged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements Servicer<Pedido>{

    private PedidoRepository pedidoRepository;

    private ProdutoRepository produtoRepository;

    private PedidoDetalheRepository pedidoDetalheRepository;

    private final List<Pedido> pedido = new ArrayList<Pedido>();

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, PedidoDetalheRepository pedidoDetalheRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoDetalheRepository = pedidoDetalheRepository;
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Paged<Pedido> findAll(int pageNumber, int size) {
        return null;
    }

    @Override
    public Pedido findById(int Id) {
        Optional<Pedido> result = pedidoRepository.findById(Id);

        Pedido pedido = null;
        if(result.isPresent()){
            pedido = result.get();
        }else{
            throw new RuntimeException("Pedido não encontrado"+Id);
        }

        return pedido;
    }

    @Override
    public Pedido save(Pedido pedido) {
        pedidoRepository.save(pedido);
        return pedido;
    }

    @Override
    public void deleteById(int id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Paged<Cliente> search(int pageNumber, int size, String keyword, FIELD field) {
        return null;
    }


    public void buy(int productId, int quantity){

        Pedido pedido = new Pedido(User.getTotalDetalhes(),User.getCurrentUser());
        Optional<Produto> result = produtoRepository.findById(productId);
        Produto produto = result.get();

        if(result.isPresent()){
            PedidoDetalhe pedidoDetalhe =
                    new PedidoDetalhe(produto.getPreco() * quantity, produto.getPreco(), quantity, pedido, produto);
            User.buyItem(pedidoDetalhe);
        }
    }

    public int saveCart(){

        Produto produto = produtoRepository.findById(5).get();
        Pedido pedido = new Pedido(User.getTotalDetalhes(), Calendar.getInstance().getTime(),User.getCurrentUser());
        List<PedidoDetalhe> listPedidosDetalhes = User.getListPedidosDetalhes();
        for (PedidoDetalhe detalhe : listPedidosDetalhes) {
            pedido.addDetail(detalhe);
        }
        User.clearCart();

        return pedidoRepository.save(pedido).getId();

    }
}
