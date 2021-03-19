package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.service.ClienteServiceImpl;
import com.luv2code.springboot.thymeleafdemo.service.Servicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private Servicer clienteService;

    @Autowired
    public ClienteController(ClienteServiceImpl theClienteService){clienteService = theClienteService;}

    @GetMapping("list")
    public String listClientes(Model theModel){

        List<Cliente> clientes = clienteService.findAll();

        theModel.addAttribute("clientes",clientes);

        return "clientes/list-clientes";
    }

    @GetMapping("search")
    public String searchCliente(@RequestParam("theSearchName") String theSearchName, Model model){
        List<Cliente> clientes = clienteService.search(theSearchName);
        model.addAttribute("clientes", clientes);
        return "clientes/list-clientes";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Cliente cliente = new Cliente();

        model.addAttribute("cliente", cliente);
        return "clientes/cliente-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("clienteId") int id, Model theModel){
        Cliente cliente = (Cliente) clienteService.findById(id);
        theModel.addAttribute("cliente",cliente);
        return "clientes/clientes-form";
    }
}
