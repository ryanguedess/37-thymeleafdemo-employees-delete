package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Produto;
import com.luv2code.springboot.thymeleafdemo.paging.Paged;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    public List<Produto> findAllByOrderByNomeAsc();

    public List<Produto> findAllByOrderByPrecoAsc();

    public List<Produto> findAllByOrderByPrecoDesc();
}
