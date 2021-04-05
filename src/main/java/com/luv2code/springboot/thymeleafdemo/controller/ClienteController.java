package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.FIELD;
import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.paging.Paged;
import com.luv2code.springboot.thymeleafdemo.service.ClienteServiceImpl;
import com.luv2code.springboot.thymeleafdemo.service.Servicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private Servicer clienteService;

    @Autowired
    public ClienteController(ClienteServiceImpl theClienteService){clienteService = theClienteService;}

    @GetMapping("list")
    public String listClientes(
            @RequestParam(value="pageNumber",required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int size,
            Model theModel){
        theModel.addAttribute("clientes",clienteService.findAll(pageNumber,size));

        return "clientes/list-clientes";
    }

    @GetMapping("search")
    public String searchCliente(
            @RequestParam(value = "pageNumber",required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam("keyword") String keyword,
            @RequestParam("filter") int filter,
            Model model){
        Paged<Cliente> clientes = null;
        if(filter == 1){
            clientes = clienteService.search(pageNumber,size,keyword, FIELD.CODIGO);
        }else if (filter == 2){
            clientes = clienteService.search(pageNumber, size, keyword, FIELD.RAZAO_SOCIAL);
        }else{
            clientes = clienteService.search(pageNumber, size, keyword, FIELD.NOME_FANTASIA);
        }
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
        return "clientes/cliente-form";
    }

    @PostMapping("/save")
    public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {

        clienteService.save(cliente);

        return "redirect:/clientes/list";
    }

    @PostMapping("/delete")
    public String deleteCliente(@RequestParam("clienteId") int id){
        clienteService.deleteById(id);

        return "redirect:/clientes/list";
    }
}
