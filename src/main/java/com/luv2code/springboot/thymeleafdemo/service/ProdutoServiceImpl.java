package com.luv2code.springboot.thymeleafdemo.service;

import com.luv2code.springboot.thymeleafdemo.dao.ProdutoRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements Servicer<Produto>{

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto findById(int Id) {
        Optional<Produto> result = produtoRepository.findById(Id);

        Produto produto = null;

        if(result.isPresent()){
            produto = result.get();
        }else{
            throw new RuntimeException("Produto não encontrado" + Id);
        }

        return produto;
    }

    @Override
    public Produto save(Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    @Override
    public void deleteById(int id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public List<Produto> search(String theSearchName) {
        return null;
    }
}
