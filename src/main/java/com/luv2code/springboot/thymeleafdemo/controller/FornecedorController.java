package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Fornecedor;
import com.luv2code.springboot.thymeleafdemo.service.FornecedorServiceImpl;
import com.luv2code.springboot.thymeleafdemo.service.Servicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    private Servicer fornecedorService;

    @Autowired
    public FornecedorController(FornecedorServiceImpl theFornecedorService){fornecedorService = theFornecedorService;}

    @GetMapping("/list")
    public String listFornecedores(Model theModel){

        List<Fornecedor> fornecedores = fornecedorService.findAll();

        theModel.addAttribute("fornecedores",fornecedores);

        //return "employees/list-employees";
        return "fornecedores/list-fornecedores";
    }
}
