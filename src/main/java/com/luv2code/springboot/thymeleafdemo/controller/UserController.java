package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.PedidoDetalhe;
import com.luv2code.springboot.thymeleafdemo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getCarrinho")
    public String getCarrinho(Model model){

        double total = User.getTotalDetalhes();
        model.addAttribute("total",total);
        model.addAttribute("detalhes", User.getListPedidosDetalhes());


        return "pedidos/cart-details";
    }

    @GetMapping("/saveCart")
    public String saveCart(){
        return User.saveCart();
    }
}
