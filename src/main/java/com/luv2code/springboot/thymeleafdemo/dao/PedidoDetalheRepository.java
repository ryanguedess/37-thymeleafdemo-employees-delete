package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.PedidoDetalhe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDetalheRepository extends JpaRepository<PedidoDetalhe, Integer> {
}
