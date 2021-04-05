package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.dao.PedidoDetalheRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PedidoRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.entity.Pedido;
import com.luv2code.springboot.thymeleafdemo.entity.PedidoDetalhe;
import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import com.luv2code.springboot.thymeleafdemo.model.User;
import com.luv2code.springboot.thymeleafdemo.service.ClienteServiceImpl;
import com.luv2code.springboot.thymeleafdemo.service.PedidoServiceImpl;
import com.luv2code.springboot.thymeleafdemo.service.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {


    private PedidoServiceImpl pedidoService;

    private ProdutoServiceImpl produtoService;

    private ClienteServiceImpl clienteService;

    private PedidoDetalheRepository pedidoDetalheRepository;

    private static int currentPedidoId;

    @Autowired
    public PedidoController(PedidoServiceImpl pedidoService, ProdutoServiceImpl produtoService, ClienteServiceImpl clienteService, PedidoDetalheRepository pedidoDetalheRepository) {
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.pedidoDetalheRepository = pedidoDetalheRepository;
    }

    @GetMapping("/list")
    public String listPedidos(Model model){
        model.addAttribute("pedidos",pedidoService.findAll());

        return "pedidos/list-pedidos";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam("product") int id, @RequestParam("quantity") int quantity){

        pedidoService.buy(id,quantity);
//        if(User.getCurrentPedidoId() == 0) {
//            Produto produto = produtoService.findById(id);
//            Cliente cliente = clienteService.findById(5);
//            Pedido pedido = new Pedido(produto.getPreco() , Calendar.getInstance().getTime(), cliente, User.getCurrentUser());
//            User.setCurrentPedidoId(pedidoService.save(pedido).getId());
//        }else{
//            Produto produto = produtoService.findById(id);
//            Pedido pedido = pedidoService.findById(User.getCurrentPedidoId());
//            PedidoDetalhe pedidoDetalhe =
//                    new PedidoDetalhe(produto.getPreco(), produto.getPreco(),quantity, pedido, produto);
//            pedidoDetalheRepository.save(pedidoDetalhe);
//        }
        return "redirect:/produtos/list";
    }
    @GetMapping("/detalhe")
    public String getDetalhe(@RequestParam("pedido") int id,
                             @RequestParam("titulo") String titulo,
                             Model model){
        model.addAttribute("titulo","#" + id + " - " + titulo);
        List<PedidoDetalhe> detalhes = pedidoDetalheRepository.findAll();
        model.addAttribute("pedido",
                detalhes.stream().filter(d -> d.getPedido().getId() == id).collect(Collectors.toList()));


        return "pedidos/product-details";
    }
}
