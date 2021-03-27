package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.FileUploadUtil;
import com.luv2code.springboot.thymeleafdemo.entity.Cliente;
import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import com.luv2code.springboot.thymeleafdemo.service.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoServiceImpl produtoService;

    @Autowired
    public ProdutoController(ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/list")
    public String listProdutos(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "6") int size,
            Model model){

        model.addAttribute("produtos",produtoService.findAll(pageNumber,size));

        return "produtos/list-produtos";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Produto produto = new Produto();

        model.addAttribute("produto", produto);

        return "produtos/produtos-form";
    }

    @PostMapping("/save")
    public String saveProduto(@ModelAttribute("produto")Produto produto,
                              @RequestParam("image")MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        produto.setImagem(fileName);

        Produto savedProduto = produtoService.save(produto);

        String uploadDir = "fotos-produtos/" + savedProduto.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/produtos/list";
    }
}
