package com.luv2code.springboot.thymeleafdemo.model;

import com.luv2code.springboot.thymeleafdemo.dao.PedidoDetalheRepository;
import com.luv2code.springboot.thymeleafdemo.dao.PedidoRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Pedido;
import com.luv2code.springboot.thymeleafdemo.entity.PedidoDetalhe;
import com.luv2code.springboot.thymeleafdemo.service.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class User {

    private static String USERNAME;
    private static int  currentPedidoId;
    private static List<PedidoDetalhe> listPedidosDetalhes = new ArrayList<>();

    private static PedidoServiceImpl pedidoService;

    @Autowired
    public User(PedidoServiceImpl pedidoService) {
        User.pedidoService = pedidoService;
    }

    public static String getCurrentUser(){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                USERNAME = ((UserDetails) principal).getUsername();
            } else {
                USERNAME = principal.toString();
            }
            return USERNAME;
    }

    public static int getCurrentPedidoId() {
        return currentPedidoId;
    }

    public static void setCurrentPedidoId(int currentPedidoId) {
        User.currentPedidoId = currentPedidoId;
    }

    public static void buyItem(PedidoDetalhe pedidoDetalhe){
        listPedidosDetalhes.add(pedidoDetalhe);
    }

    public static void removeItem(PedidoDetalhe pedidoDetalhe){
        listPedidosDetalhes.remove(pedidoDetalhe);
    }

    public static void clearCart(){
        listPedidosDetalhes.clear();
    }

    public static List<PedidoDetalhe> getListPedidosDetalhes(){
        return listPedidosDetalhes;
    }

    public static double getTotalDetalhes(){
        return listPedidosDetalhes.stream().mapToDouble(x -> x.getTotal()).sum();
    }

    public static String saveCart(){

        int id = pedidoService.saveCart();

        return "redirect:/pedidos/detalhe?pedido=" + id + "&titulo="+"SEM TÍTULO";
    }
}
